package hu.onlab.easylearner.model.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LEARNERS")
@Data
public class LearnersKT {
    @Id
    @Column(name = "USERID")
    Integer userId;

    @Basic
    @Column(name = "NAME", length = 50)
    String name;

    @Basic
    @Column(name = "IDCARDNUMBER", length = 50)
    String idCardNumber;

    @Basic
    @Column(name = "PROFILEIMAGE", length = 20)
    String profileImage;

    @Basic
    @Column(name = "USERNAME", length = 20)
    String username;

    @Basic
    @Column(name = "PASSWORD", length = 50)
    String password;


}