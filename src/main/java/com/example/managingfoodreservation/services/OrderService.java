package com.example.managingfoodreservation.services;


import com.example.managingfoodreservation.dto.OrderDto;
import com.example.managingfoodreservation.model.Order;
import com.example.managingfoodreservation.model.Staff;

import java.time.Instant;
import java.util.List;

public interface OrderService {
    OrderDto save(OrderDto dto);
    OrderDto findByName(String name);

    OrderDto findById(Integer id_order);

    OrderDto findByDate(Instant date);
    OrderDto findByNumber(Integer number);
    OrderDto findByStaff(Staff staff);
    OrderDto findByOrder(Order order);
    void delete (Integer id_order);

}
