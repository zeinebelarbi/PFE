package com.example.managingfoodreservation.dto;

import com.example.managingfoodreservation.model.Order;
import com.example.managingfoodreservation.model.Staff;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;

@Builder
@Data
public class OrderDto {
    private Integer id;
    private String name;

    private Instant date;

    private Integer number;

    private Staff staff;

    private Order order;

    public static OrderDto fromEntity(Order order){
    if(order == null){
        return null;
    }
    return OrderDto.builder()
            .name(order.getName())
            .date(order.getDate())
            .number(order.getNumber())
            .build();
}
    public static Order toEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        Staff staff = orderDto.getStaff();
        return Order.builder()
                .name(orderDto.getName())
                .date(orderDto.getDate())
                .number(orderDto.getNumber())
                .staff(staff)
                .build();
    }

    public void Order(String name, Instant date, Integer number, Staff staff, Order order) {
        this.name = name;
        this.date = date;
        this.number = number;
        this.staff = staff;
        this.order = order;
    }

    public Order getOrders() {
        return order;
    }
}
