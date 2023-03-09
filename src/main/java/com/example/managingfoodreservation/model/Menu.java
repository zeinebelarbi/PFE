package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Menu")
public class Menu  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id_menu")
    private Integer id ;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes = new ArrayList<>();






}
