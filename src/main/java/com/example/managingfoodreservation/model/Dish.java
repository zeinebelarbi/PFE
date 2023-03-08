package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;


@Data
@Builder

@AllArgsConstructor

@Entity
@Table(name="Dishes")
public class Dish extends AbstractEntity {
    @Column(name="Dishname",nullable = false)
    private String dishname ;
    @Column(name="Quantity",nullable = false)
    private Integer quantity;
    @Column(name="OrderTime",nullable = false)
    private Instant orderTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="Menu",nullable = false)
    private Menu menu;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    Dish(Integer id, Instant creationDate, Instant lastUpdateDate) {
        super(id, creationDate, lastUpdateDate);
    }


    public Dish() {

        super();
    }
}
