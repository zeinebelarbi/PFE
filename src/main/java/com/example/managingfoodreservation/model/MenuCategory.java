package com.example.managingfoodreservation.model;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;
@NamedQuery(name=" MenuCategory.getAllMenuCategory",query = "select men_c from MenuCategory men_c where men_c.id in(select d.menuCategory from Dish d where d.status='true')  ")


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="MenuCategory")
public class MenuCategory implements Serializable {
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="idMenuCategory")
    private Integer idMenuCategory;

    @Column(name ="name")
    private String menucategoryname;

}

