package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.dto.DishDto;
import com.example.managingfoodreservation.dto.MenuDto;
import com.example.managingfoodreservation.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;


@RestController
@RequestMapping("/menu")
public abstract class MenuController{

    private MenuService menuservice;
    @Autowired
    public MenuController(MenuService menuservice){
        this.menuservice=menuservice;
    }
    @PostMapping(value =APP_ROOT+ "/menu/create")
    public MenuDto save(@RequestBody MenuDto dto) {

        return menuservice.save(dto);
    }


    @GetMapping(value =APP_ROOT+"/menu/{id}")
    public MenuDto findById(Integer id) {

        return menuservice.findById(id);
    }

    @GetMapping(value =APP_ROOT+"/menu/all")
    public List<DishDto> findAll() {
        return (List<DishDto>) menuservice;
    }


    @DeleteMapping(value =APP_ROOT+"/menu/delete/{id}" )
    public void delete(Integer id) {

        menuservice.delete(id);
    }
}

