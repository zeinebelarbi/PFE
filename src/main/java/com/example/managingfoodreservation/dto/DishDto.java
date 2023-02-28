package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Dish;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class DishDto {
    private Integer id;
    private String Dishname ;

    private BigDecimal Quantity;

    private Instant OrderTime;

    private MenuDto menu;
    public DishDto fromEntity(Dish dish){
       if(dish ==null){
        return null;
    }
    return DishDto.builder()
            .Dishname(dish.getDishname())
            .Quantity(dish.getQuantity())
            .OrderTime(dish.getOrderTime())

            .build();
}
public Dish toEntity (DishDto dishDto){
    if(dishDto== null){
      return null;
    }
    Dish dish = new Dish();
    dish.setDishname(dishDto.getDishname());
    dish.setQuantity(dishDto.getQuantity());
      return dish;
    }
}
