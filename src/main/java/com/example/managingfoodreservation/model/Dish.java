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
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Dish")
public class Dish extends AbstractEntity {
    @Column(name="Dishname")
    private String Dishname ;
    @Column(name="Quantity")
    private Integer Quantity;
    @Column(name="OrderTime")
    private Instant OrderTime;
    @ManyToOne()
    @JoinColumn(name ="Menu")
    private Menu menu;
    @Column (name="id_dish")
    private Integer id ;

    Dish(Integer id, Instant creationDate, Instant lastUpdateDate) {
        super(id, creationDate, lastUpdateDate);
    }


    public Dish() {
        super();
    }
}
