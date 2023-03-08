package com.example.managingfoodreservation.controller.Api;

import com.example.managingfoodreservation.dto.DishDto;

import com.example.managingfoodreservation.model.Menu;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

public interface DishApi {
    @PostMapping(value =APP_ROOT+ "/dish/create")
   DishDto save(DishDto dto);
    @GetMapping(value =APP_ROOT+"/dish/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findById(Integer id);
    @GetMapping(value =APP_ROOT+"/dish/{Dish name}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByDishName(String dishname);
    @GetMapping(value =APP_ROOT+"/dish/{Quantity}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByQuantity(Integer quantity);

    @GetMapping(value =APP_ROOT+"/dish/{menu}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByMenu(Menu menu);

    @GetMapping(value =APP_ROOT+"/dish/{OrderTime}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByOrderTime(Instant orderTime);

    @GetMapping(value =APP_ROOT+"/dish/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<DishDto> findAll();
    @DeleteMapping(value =APP_ROOT+"/dish/delete/{id}" )

    void delete(Integer id);

}
