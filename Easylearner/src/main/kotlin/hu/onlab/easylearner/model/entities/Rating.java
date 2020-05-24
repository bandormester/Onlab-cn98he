package hu.onlab.easylearner.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RATING")
public class Rating {
    @Id
    Integer Id;

    @Basic
    @Column(name = "TEACHERID")
    Integer TeacherId;

    @Basic
    @Column(name = "TOPICID")
    Integer TopicId;

    @Basic
    @Column(name = "PUNCTUALITY")
    Integer Punctiality;

    @Basic
    @Column(name = "COMMUNICATION")
    Integer Communication;

    @Basic
    @Column(name = "KNOWLEDGE")
    Integer Knowledge;

    @Basic
    @Column(name = "TEXT")
    String Text;
}
