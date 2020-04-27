package hu.onlab.easylearner.model.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LEVELS")
public class Level {
    @Id
    Integer Id;

    @Basic
    @Column(name = "NAME")
    String Name;
}
