package hu.onlab.easylearner.model.service;

import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {
    TopicRepository topicRepository;

    public List<Topic> findAllQueries(){
        return topicRepository.findAll();
    }
}
