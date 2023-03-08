package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.controller.Api.MenuApi;
import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public abstract class MenuController implements MenuApi {
    @Autowired
    private MenuService menuservice;
    @Autowired
    public MenuController(MenuService menuservice){
        this.menuservice=menuservice;

    }
    @Autowired
    public MenuService getMenuservice(){

        return menuservice;
    }
    @Override
    public MenuDto save(MenuDto dto) {

        return menuservice.save(dto);
    }


    @Override
    public MenuDto findById(Integer id) {

        return menuservice.findById(id);
    }

    @Override
    public List<DishDto> findAll() {
        return (List<DishDto>) menuservice;
    }


    @Override
    public void delete(Integer id) {

        menuservice.delete(id);
    }
}

