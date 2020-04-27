package hu.onlab.easylearner.model.repository;

import hu.onlab.easylearner.model.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> { }
