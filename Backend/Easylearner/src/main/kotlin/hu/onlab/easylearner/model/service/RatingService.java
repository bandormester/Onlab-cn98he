package hu.onlab.easylearner.model.service;

import hu.onlab.easylearner.model.dto.LearnerProfileDto;
import hu.onlab.easylearner.model.dto.RatingWithNameDto;
import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.entities.Rating;
import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.repository.LearnerRepository;
import hu.onlab.easylearner.model.repository.RatingRepository;
import hu.onlab.easylearner.model.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {
    RatingRepository ratingRepository;
    TopicRepository topicRepository;
    LearnerRepository learnerRepository;
    @PersistenceContext
    EntityManager em;

    public List<RatingWithNameDto> getRating(Integer id){
        List<Rating> help = ratingRepository.findAll();

        List<RatingWithNameDto> result = new ArrayList<>();

        for(Rating r : help){
            if(r.getTeacherId().equals(id)){
                Optional<Topic> topic = topicRepository.findById(r.getTopicId());
                result.add(
                        new RatingWithNameDto(
                            r.getId(), r.getTeacherId(), topic.get().getName(), r.getPunctiality(), r.getCommunication(), r.getKnowledge(), r.getText()
                        )
                );
            }
        }

        return result;
    }

    public LearnerProfileDto getProfileRating(Integer id){
        String picUrl="";
        String fullName="";
        String userName="";
        Double punctuality=0.0;
        Double knowledge=0.0;
        Double communication=0.0;

        Optional<LearnersKT> learner = learnerRepository.findById(id);

        if(learner.isPresent()){
            picUrl = learner.get().getProfileImage();
            fullName = learner.get().getName();
            userName = learner.get().getUsername();
        } else return null;

        List<Rating> ratings = em.createQuery("SELECT r FROM Rating r WHERE r.TeacherId = :id").setParameter("id",id).getResultList();

        for(Rating r : ratings){
            punctuality+=r.getPunctiality();
            communication+=r.getCommunication();
            knowledge+=r.getKnowledge();
        }
        punctuality = Math.round(punctuality/ratings.size()*10)/10.0;
        communication = Math.round(communication/ratings.size()*10)/10.0;
        knowledge = Math.round(knowledge/ratings.size()*10)/10.0;

        return new LearnerProfileDto(picUrl, fullName, userName, punctuality, knowledge, communication, ratings);
    }

    public void addRating(Integer lessonId, Integer ratedId, String topicName, Integer comm, Integer know, Integer punc, String text) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("AddRating");

        query.registerStoredProcedureParameter("idOfTeacher", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idOfTopic", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("punc", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("comm", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("know", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("rateText", String.class, ParameterMode.IN);

        query.setParameter("idOfTeacher", ratedId);

        List<Topic> topics = topicRepository.findAll();
        int topicId = 1;
        for(Topic t : topics) if(t.getName().equals(topicName)) topicId = t.getId();

        query.setParameter("idOfTopic", topicId);
        query.setParameter("punc", punc);
        query.setParameter("comm", comm);
        query.setParameter("know", know);
        query.setParameter("rateText", text);

        query.execute();
    }
}
