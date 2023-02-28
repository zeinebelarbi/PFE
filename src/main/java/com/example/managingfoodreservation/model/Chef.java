package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;


@Data
@Builder

@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Chef")
public class Chef extends AbstractEntity {
    @Column (name="id_chef")
    private Integer id ;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="Canteenworker ")
    private Canteenworker canteenworker  ;
    @Column (name ="DeliverTime")
    private Instant DeliverTime;

    Chef(Integer id, Instant creationDate, Instant lastUpdateDate) {

        super(id, creationDate, lastUpdateDate);
    }

    public Chef() {

        super();
    }
}
