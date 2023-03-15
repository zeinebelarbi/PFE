package com.example.managingfoodreservation.model;


import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Dishes")
public class Dish  {
    @Column(name="Dishname",nullable = false)
    private String dishname ;
    @Column(name="Quantity",nullable = false)
    private Integer quantity;
    @Column(name="OrderTime",nullable = false)
    private Instant orderTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="Menu",nullable = true)
    private Menu menu;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_dish")
    private Integer id ;




}
