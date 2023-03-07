package com.example.managingfoodreservation.controller;


import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.model.Menu;
import com.example.managingfoodreservation.services.DishService;
import com.example.managingfoodreservation.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.time.Instant;
import java.util.List;

@RestController
public class DishController implements DishApi{
    @Autowired
    private DishService dishservice;
    @Autowired
    public DishController(MenuService menuservice){
        this.dishservice=dishservice;

    }


    @Override
    public DishDto save(DishDto dto) {
        return dishservice.save(dto);
    }

    @Override
    public DishDto findById(Integer id) {
        return dishservice.findById(id);
    }

    @Override
    public DishDto findByDishName(String Dishname) {
        return dishservice.findByDishName(Dishname);
    }

    @Override
    public DishDto findByQuantity(Integer Quantity) {
        return dishservice.findByQuantity(Quantity);
    }

    @Override
    public DishDto findByMenu(Menu menu) {
        return dishservice.findByMenu (menu);
    }

    @Override
    public DishDto findByOrderTime(Instant OrderTime) {
        return dishservice.findByOrderTime(OrderTime);
    }

    @Override
    public List<MenuDto> findAll() {
        return null;
    }


    @Override
    public void delete(Integer id) {

        dishservice.delete(id);
    }
}
