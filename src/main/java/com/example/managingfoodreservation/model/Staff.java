package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name="User")
public class Staff  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_User",insertable=false, updatable=false)
    private Integer id ;

    @Column(name="Firstname")
    private String Firstname;

    @Column(name="Lastname")
    private String Lastname;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name= "password")
    private String password;
    @Column(name="phone")
    private String phone;
    @Column(name="birth_date")
    private Date birth_date;
    @OneToMany(mappedBy ="staff")
    private List<Order> orders;




  



}
