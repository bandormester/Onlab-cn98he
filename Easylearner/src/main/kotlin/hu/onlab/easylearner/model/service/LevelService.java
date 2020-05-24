package hu.onlab.easylearner.model.service;

import hu.onlab.easylearner.model.entities.Level;
import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.repository.LevelRepository;
import hu.onlab.easylearner.model.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LevelService {
    LevelRepository levelRepository;

    public List<Level> findAllQueries(){
        return levelRepository.findAll();
    }
}
