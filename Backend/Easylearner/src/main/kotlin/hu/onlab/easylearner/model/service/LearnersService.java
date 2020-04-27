package hu.onlab.easylearner.model.service;

import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.repository.LearnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LearnersService {

    //LearnersMapper learnersMapper;
    LearnerRepository learnerRepository;

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
