package com.example.managingfoodreservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Data
@Builder(toBuilder = true)

@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Canteenworker")
public class Canteenworker extends AbstractEntity {
    @Column (name="id_canteenworker")
    private Integer id ;
    @Column(name="name")
    private String name;

    @Column(name="orderprice")
    private BigDecimal OrderPrice;
    @OneToMany(mappedBy ="canteenworker")
    private List<Chef>chefs;
    @Column(name="OrderTime")
    private Instant OrderTime;

    public Canteenworker(Integer id, Instant creationDate, Instant lastUpdateDate) {
        super(id, creationDate, lastUpdateDate);

        this.creationDate = creationDate ;
    }


    public Canteenworker() {

        super();
    }

}

