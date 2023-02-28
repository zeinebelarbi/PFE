package com.example.managingfoodreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder

@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="Menu")
public class Menu extends AbstractEntity {
    @Column (name="id_menu")
    private Integer id ;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes = new ArrayList<>();



    Menu(Integer id, Instant creationDate, Instant lastUpdateDate) {

        super(id, creationDate, lastUpdateDate);
    }

    public Menu() {

        super();
    }
}
