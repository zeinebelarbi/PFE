package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Dish;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class DishDto {
    private Integer id;
    private String dishname ;

    private Integer quantity;

    public Instant orderTime;

    private MenuDto menu;
    public static DishDto fromEntity(Dish dish){
       if(dish ==null){
        return null;
    }
    return DishDto.builder()
            .dishname(dish.getDishname())
            .quantity(dish.getQuantity())
            .orderTime(dish.getOrderTime())

            .build();
}
public static Dish toEntity(DishDto dishDto){
    if(dishDto== null){
      return null;
    }
    Dish dish = new Dish();
    dish.setDishname(dishDto.getDishname());
    dish.setQuantity(dishDto.getQuantity());
    dish.setOrderTime(dishDto.getOrderTime());
      return dish;
    }
}
