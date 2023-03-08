package com.example.managingfoodreservation.controller;


import com.example.managingfoodreservation.controller.Api.DishApi;
import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.model.Menu;
import com.example.managingfoodreservation.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public abstract class DishController implements DishApi {
    private DishService dishService;

    @Autowired
    public DishController(DishService dishService){
        this.dishService=dishService;

    }


    @Override
    public DishDto save(DishDto dto) {
        
        return dishService.save(dto);
    }

    @Override
    public DishDto findById(Integer id) {
        
        return dishService.findById(id);
    }

    @Override
    public DishDto findByDishName(String dishname) {
        return dishService.findByDishName(dishname);
    }

    @Override
    public DishDto findByQuantity(Integer quantity) {
        return dishService.findByQuantity(quantity);
    }

    @Override
    public DishDto findByMenu(Menu menu) {

        return dishService.findByMenu (menu);
    }

    @Override
    public DishDto findByOrderTime(Instant orderTime) {

        return dishService.findByOrderTime(orderTime);
    }

    @Override
    public List<DishDto> findAll() {
        return dishService.findAll();
    }


    @Override
    public void delete(Integer id) {

        dishService.delete(id);
    }
}
