package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.util.List;


@Data
@Builder(toBuilder = true)

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Canteenworker")
public class Canteenworker{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id_canteenworker")
    private Integer id ;
    @Column(name="name")
    private String name;

    @Column(name="orderprice")
    private Double OrderPrice;
    @OneToMany(mappedBy ="canteenworker")
    private List<Chef>chefs;
    @Column(name="OrderTime")
    private Instant OrderTime;




}

