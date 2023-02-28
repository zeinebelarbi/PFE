package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.ListofOrders;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Builder
@Data
public class ListofOrdersDto {
    private Integer id;
    private OrderDto order;
    private List<CanteenworkerDto> Canteenworkername;
    public ListofOrdersDto fromEntity(ListofOrders listoforders){
        if(listoforders ==null){
            return null;
        }
        return ListofOrdersDto .builder()
                .build();
    }
    public ListofOrders toEntity (ListofOrdersDto listofOrdersDto){
        if(listofOrdersDto== null){
            return null;
        }
        ListofOrders  listofOrders  = new ListofOrders ();

        return  listofOrders;
    }
}
