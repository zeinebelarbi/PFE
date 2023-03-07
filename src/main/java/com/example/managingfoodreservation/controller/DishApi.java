package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.model.Menu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

public interface DishApi {
    @PostMapping(value =APP_ROOT+ "/dish/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   DishDto save(DishDto dto);
    @GetMapping(value =APP_ROOT+"/dish/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findById(Integer id);
    @GetMapping(value =APP_ROOT+"/dish/{Dishname}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByDishName(String Dishname );
    @GetMapping(value =APP_ROOT+"/dish/{Quantity}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByQuantity(Integer Quantity );

    @GetMapping(value =APP_ROOT+"/dish/{menu}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByMenu(Menu menu );

    @GetMapping(value =APP_ROOT+"/dish/{OrderTime}",produces = MediaType.APPLICATION_JSON_VALUE)
    DishDto findByOrderTime(Instant OrderTime );

    @GetMapping(value =APP_ROOT+"/dish/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<MenuDto> findAll();
    @DeleteMapping(value =APP_ROOT+"/dish/delete/{id}" )

    void delete(Integer id);
}
