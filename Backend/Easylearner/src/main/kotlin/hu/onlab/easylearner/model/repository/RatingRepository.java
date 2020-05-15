package hu.onlab.easylearner.model.repository;

import hu.onlab.easylearner.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> { }
