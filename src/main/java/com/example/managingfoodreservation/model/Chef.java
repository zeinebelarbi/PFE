package com.example.managingfoodreservation.model;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_chef")
    private Integer id;
    @Column(name="name",insertable = false,updatable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name ="Canteenworker ",insertable = false,updatable = false)
    private Canteenworker canteenworker  ;
    @Column(name ="DeliverTime")
    private Instant DeliverTime;




}
