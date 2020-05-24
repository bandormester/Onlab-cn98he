package hu.onlab.easylearner.model.repository;

import hu.onlab.easylearner.model.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {}
