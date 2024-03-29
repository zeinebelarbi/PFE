package com.example.managingfoodreservation.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name="Dish.getAllDish",query = "select new com.example.managingfoodreservation.wrapper.DishWrapper(d.iddish, d.dishname, d.description, d.price, d.status, d.menuCategory.idMenuCategory, d.menuCategory.menucategoryname) from Dish d")

@NamedQuery(name=" Dish.updateStatus",query = "update Dish d set d.status=:status where d.iddish=:iddish ")
@NamedQuery(name="Dish.getDishByMenuCategory", query = "select new com.example.managingfoodreservation.wrapper.DishWrapper(d.iddish, d.dishname, d.description, d.price, d.status, d.menuCategory.idMenuCategory, d.menuCategory.menucategoryname) from Dish d where d.menuCategory.idMenuCategory=:idMenuCategory")




@NamedQuery(name="Dish.getDishById",query="select new com.example.managingfoodreservation.wrapper.DishWrapper(d.iddish,d.dishname,d.description,d.price)from Dish d where d.iddish=:iddish ")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="Dish")
public class Dish implements Serializable {
    private static final long serialVersionUID =123456L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id")
    private Integer iddish;
    @Column(name ="name")
    private String dishname;

    @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="menucategory",nullable = false)
    private MenuCategory menuCategory;
    @Column(name ="description")
    private String description;

    @Column(name ="price")
    private Float price;

    @Column(name="status")
    private String status;
}
