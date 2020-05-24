package hu.onlab.easylearner.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TOPICS")
public class Topic {
    @Id
    Integer Id;

    @Basic
    @Column(name = "NAME")
    String Name;
}
