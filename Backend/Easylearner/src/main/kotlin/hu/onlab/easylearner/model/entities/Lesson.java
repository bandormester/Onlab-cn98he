package hu.onlab.easylearner.model.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LESSON")
public class Lesson {
    @Id
    Integer Id;

    @Basic
    @Column(name = "TEACHERID")
    Integer TeacherId;

    @Basic
    @Column(name = "STUDENTID")
    Integer StudentId;

    @Basic
    @Column(name = "INFO")
    String Info;

    @Basic
    @Column(name = "STARTTIME")
    Long StartTime;

    @Basic
    @Column(name = "ENDTIME")
    Long EndTime;

    @Basic
    @Column(name = "LONGITUDE")
    String Longitude;

    @Basic
    @Column(name = "LATITUDE")
    String Latitude;

    @Basic
    @Column(name = "PAYMENT")
    Integer Payment;

    @Basic
    @Column(name = "LEVELID")
    Integer LevelId;

    @Basic
    @Column(name = "TOPICID")
    Integer TopicId;

    @Basic
    @Column(name = "OWNERID")
    Integer OwnerId;
}
