package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Order")
public class Order extends AbstractEntity {
    @Column (name="id_order")
    private Integer id ;

    @Column(name ="name",insertable = false,updatable = false)
    private String name;

    @Column(name ="date")
    private Instant date;

    @Column(name ="number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name="id_User")

    private Staff staff;




    public void forEach(Object o) {
    }
}
