package hu.onlab.easylearner.model.repository

import hu.onlab.easylearner.model.entities.LearnersKT
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LearnerRepository : JpaRepository<LearnersKT, Integer>{}