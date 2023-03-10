package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.model.Chef;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;


@Builder
@Data
public class ChefDto {

    private Integer id ;
    private String name;



    private Canteenworker canteenworker  ;

    private Instant DeliverTime;


    public static ChefDto fromEntity(Chef chef){
        if(chef ==null){
            return null;
        }
        return ChefDto.builder()
                .name(chef.getName())
                .DeliverTime(chef.getDeliverTime())
                .canteenworker(chef.getCanteenworker())
                .build();
    }
    public static Chef toEntity(ChefDto chefDto){
        if(chefDto ==null){
            return null;
        }
        Chef chef = new Chef();
        chef.setName(chefDto.getName());
           return chef;
    }

    public Chef get() {
        return get();
    }
}
