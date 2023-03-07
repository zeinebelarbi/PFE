package com.example.managingfoodreservation.services;

import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.model.Menu;

import java.time.Instant;
import java.util.List;

public interface DishService {
    DishDto save(DishDto dto);

    DishDto findById(Integer id);

    List<DishDto> findAll();

    void delete(Integer id);

    DishDto findByDishName(String dishname);

    DishDto findByQuantity(Integer quantity);

    DishDto findByMenu(Menu menu);

    DishDto findByOrderTime(Instant orderTime);
}
