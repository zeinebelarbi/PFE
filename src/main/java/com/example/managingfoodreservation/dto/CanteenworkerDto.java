package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Canteenworker;
import lombok.Builder;
import lombok.Data;



import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CanteenworkerDto {
    public CanteenworkerDto fromEntity;
    private String name;
    private Integer id;
    private List<ListofOrdersDto> orders;
    private Double orderPrice;
    private List<ChefDto> chefs;
    private Instant orderTime;
    public static CanteenworkerDto fromEntity(Canteenworker canteenworker) {
        if (canteenworker == null) {
            return null;
        }
        return CanteenworkerDto.builder()
                .name(canteenworker.getName())
                .id(canteenworker.getId())
                .orderPrice(canteenworker.getOrderPrice())
                .orderTime(canteenworker.getOrderTime())
                .build();
    }
    public static Canteenworker toEntity(CanteenworkerDto canteenworkerDto) {
        Canteenworker canteenworker = new Canteenworker();
        canteenworker.setName(canteenworkerDto.getName());
        canteenworker.setId(canteenworkerDto.getId());
        canteenworker.setOrderPrice(canteenworkerDto.getOrderPrice());
        return canteenworker;
    }
}
