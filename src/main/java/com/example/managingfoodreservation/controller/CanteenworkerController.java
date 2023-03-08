package com.example.managingfoodreservation.controller;




import com.example.managingfoodreservation.controller.Api.CanteenworkerApi;
import com.example.managingfoodreservation.dto.CanteenworkerDto;

import com.example.managingfoodreservation.services.CanteenworkerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public abstract class CanteenworkerController implements CanteenworkerApi {
@Autowired
private CanteenworkerService canteenworkerservice;
@Autowired
public CanteenworkerService getCanteenworkerservice(){

    return canteenworkerservice;
}
@Autowired
public CanteenworkerController(CanteenworkerService canteenworkerservice){
    this.canteenworkerservice=canteenworkerservice;

}
    @Override
    public CanteenworkerDto save(CanteenworkerDto dto) {

    return canteenworkerservice.save(dto);
    }

    @Override
    public CanteenworkerDto findByName(String name) {

    return canteenworkerservice.findByName(name);
    }

    @Override
    public CanteenworkerDto findById(Integer id) {

    return canteenworkerservice.findById(id);
    }

    @Override
    public CanteenworkerDto findByOrderPrice(Double OrderPrice) {
        return canteenworkerservice.findByOrderPrice(OrderPrice);
    }

    @Override
    public CanteenworkerDto findByOrderTime(Instant OrderTime) {
        return canteenworkerservice.findByOrderTime(OrderTime);
    }

    @Override
    public List<CanteenworkerDto> findAll() {

    return canteenworkerservice.findAll();
    }

    @Override
    public void delete(Integer id) {

    canteenworkerservice.delete(id);
    }
}




