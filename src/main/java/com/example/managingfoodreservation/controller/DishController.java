package com.example.managingfoodreservation.controller;


import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.model.Menu;
import com.example.managingfoodreservation.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping("/dishes")
public abstract class DishController  {
    private DishService dishService;

    @Autowired
    public DishController(DishService dishService){
        this.dishService=dishService;

    }

    @PostMapping(value =APP_ROOT+ "/dish/create")

    public DishDto save(DishDto dto) {
        
        return dishService.save(dto);
    }

    @GetMapping(value =APP_ROOT+"/dish/{id}")

    public DishDto findById(Integer id) {
        
        return dishService.findById(id);
    }

    @GetMapping(value =APP_ROOT+"/dish/{Dish name}")
    public DishDto findByDishName(String dishname) {
        return dishService.findByDishName(dishname);
    }

    @GetMapping(value =APP_ROOT+"/dish/{Quantity}")
    public DishDto findByQuantity(Integer quantity) {
        return dishService.findByQuantity(quantity);
    }


    @GetMapping(value =APP_ROOT+"/dish/{menu}")
    public DishDto findByMenu(Menu menu) {

        return dishService.findByMenu (menu);
    }


    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderTime}")
    public DishDto findByOrderTime(Instant orderTime) {

        return dishService.findByOrderTime(orderTime);
    }


    @GetMapping(value =APP_ROOT+"/dish/all")
    public List<DishDto> findAll() {
        return dishService.findAll();
    }



    @DeleteMapping(value =APP_ROOT+"/dish/delete/{id}" )
    public void delete(Integer id) {

        dishService.delete(id);
    }
}
