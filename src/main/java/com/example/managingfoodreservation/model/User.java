package com.example.managingfoodreservation.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;


@NamedQuery(name ="User.findByEmailId",query = "select u from User u where u.email=:email")
@NamedQuery(name=" User.getAllUser",query = "select new com.example.managingfoodreservation.wrapper.UserWrapper(u.id, u.username, u.email, u.password, u.role,u.status) from User u where u.role='user'")
@NamedQuery(name="User.updateStatus",query="update User u set u.status=:status where u.id=:id")
@NamedQuery(name=" User.getAllAdmin",query = "select   u.email from User u where u.role='admin'")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="user")
public class User implements Serializable {



private static final long serialVersionID =1L;
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;
    @Column(name ="username")
    private String username;


    @Column(name ="email")
    private String email;


    @Column(name ="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name="status")
    private String status;


}
