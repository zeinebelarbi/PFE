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

    private CompanyDto Companyname;
    public MenuDto fromEntity(Menu menu){
        if(menu== null){
            return null;
        }
        return MenuDto.builder()

                .build();
    }
    public Menu toEntity (MenuDto menuDto){
        Menu menu = new Menu();
        return menu;
    }
}
