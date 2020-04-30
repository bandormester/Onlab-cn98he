package hu.onlab.easylearner.model.service;

import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.repository.LearnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class LearnersService {

    @PersistenceContext
    EntityManager em;

    //LearnersMapper learnersMapper;
    LearnerRepository learnerRepository;

    public void addLearner(byte[] pic, String name, String idCardNumber, String username, String password) {
        boolean copied = true;
        String profileImage = String.valueOf(name.hashCode());//hashName(name);
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(pic));
            ImageIO.write(image, "JPG", new File("F:\\Android\\Work\\Onlab\\Backend\\Easylearner\\src\\main\\resources\\images\\"+profileImage+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            copied = false;
        }
        if(copied){
            StoredProcedureQuery query = em.createStoredProcedureQuery("AddLearner");

            query.registerStoredProcedureParameter("fullName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idCardNumber", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("profImage", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("password", String.class, ParameterMode.IN);

            query.setParameter("fullName", name);
            query.setParameter("idCardNumber", idCardNumber);
            query.setParameter("profImage", profileImage);
            query.setParameter("userName", username);
            query.setParameter("password", password);

            query.execute();
        }
    }

    public List<LearnersKT> findAllQueries()  {
         return learnerRepository.findAll();

         //return findResult.stream().map(item -> learnersMapper.entityToDto(item)).collect(Collectors.toList());
    }

    public Boolean tryLogin(String username, String password){
         List<LearnersKT> learners = learnerRepository.findAll();
         for(LearnersKT l : learners){
             if(l.getUsername().equals(username)){
                 System.out.println(password +" == "+ l.getPassword());
                 return l.getPassword().equals(password);
             }
         }
         return false;
    }
}
