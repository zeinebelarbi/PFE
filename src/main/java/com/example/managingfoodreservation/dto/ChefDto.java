package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.model.Chef;
import com.example.managingfoodreservation.model.ListofOrders;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;
import java.util.List;

@Builder
@Data
public class ChefDto {

    private Integer id ;
    private String name;

    private List<ListofOrders> preparedorder;

    private Canteenworker canteenworker  ;

    private Instant DeliverTime;


    public ChefDto fromEntity (Chef chef){
        if(chef ==null){
            return null;
        }
        return ChefDto.builder()
                .name(chef.getName())
                .DeliverTime(chef.getDeliverTime())
                .canteenworker(chef.getCanteenworker())
                .build();
    }
    public Chef toEntity (ChefDto chefDto){
        if(chefDto ==null){
            return null;
        }
        Chef chef = new Chef();
        chef.setName(chefDto.getName());
           return chef;
    }
}
