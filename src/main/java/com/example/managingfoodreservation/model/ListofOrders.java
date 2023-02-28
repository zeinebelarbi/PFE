package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(name="ListofOrders")
public class ListofOrders extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name="order")
    private Order order;
    @Column (name="id_Listoforders")
    private Integer id ;


    ListofOrders(Integer id, Instant creationDate, Instant lastUpdateDate) {
        super(id, creationDate, lastUpdateDate);
    }
}

