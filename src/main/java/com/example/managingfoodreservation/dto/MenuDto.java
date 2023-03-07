package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Menu;
import lombok.Builder;
import lombok.Data;


import java.util.List;
@Builder
@Data
public class MenuDto {

    private Integer id;
    private List <DishDto>Dishes;


    public static MenuDto fromEntity(Menu menu){
        if(menu== null){
            return null;
        }
        return MenuDto.builder()

                .build();
    }
    public static Menu toEntity(MenuDto menuDto){
        Menu menu = new Menu();
        return menu;
    }
}
