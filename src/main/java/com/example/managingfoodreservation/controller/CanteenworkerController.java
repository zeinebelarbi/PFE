package com.example.managingfoodreservation.controller;





import com.example.managingfoodreservation.dto.CanteenworkerDto;

import com.example.managingfoodreservation.services.CanteenworkerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping("/canteenworkers")
public abstract class CanteenworkerController{

private CanteenworkerService canteenworkerservice;

@Autowired
public CanteenworkerController(CanteenworkerService canteenworkerservice){
    this.canteenworkerservice=canteenworkerservice;

}
    @PostMapping(value =APP_ROOT+ "/canteenworker/create")
    public CanteenworkerDto save(@RequestBody CanteenworkerDto dto) {

    return canteenworkerservice.save(dto);
    }

    @GetMapping(value =APP_ROOT+"/canteenworker/{name}")
    public CanteenworkerDto findByName(String name) {

    return canteenworkerservice.findByName(name);
    }
    @GetMapping(value =APP_ROOT+"/canteenworker/{id}")
    public CanteenworkerDto findById(Integer id) {

    return canteenworkerservice.findById(id);
    }

    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderPrice}")
    public CanteenworkerDto findByOrderPrice(Double OrderPrice) {
        return canteenworkerservice.findByOrderPrice(OrderPrice);
    }

    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderTime}")
    public CanteenworkerDto findByOrderTime(Instant OrderTime) {
        return canteenworkerservice.findByOrderTime(OrderTime);
    }

    @GetMapping(value =APP_ROOT+"/canteenworker/all")
    public List<CanteenworkerDto> findAll() {

    return canteenworkerservice.findAll();
    }

    @DeleteMapping(value =APP_ROOT+"/canteenworker/delete/{id}" )
    public void delete(Integer id) {

    canteenworkerservice.delete(id);
    }
}




