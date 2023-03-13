package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.dto.ChefDto;

import com.example.managingfoodreservation.model.Canteenworker;
import com.example.managingfoodreservation.services.ChefService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.time.Instant;
import java.util.List;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping("/chefs")
public abstract class ChefController {

    private ChefService chefservice;
    @Autowired
    public ChefController( ChefService chefservice){

        this.chefservice=chefservice;
    }
    @PostMapping(value =APP_ROOT+ "/chef/create")
    public ChefDto save(@RequestBody ChefDto dto) {


        return chefservice.save(dto);
    }


    @GetMapping(value =APP_ROOT+"/chef/{id_chef}")
    public ChefDto findById(Integer id ) {

        return chefservice.findById(id);
    }


    @GetMapping(value =APP_ROOT+"/chef/name")
    public ChefDto findByName(String name) {

        return chefservice.findByName(name);
    }

    @GetMapping(value =APP_ROOT+"/chef/delivertime")
    public ChefDto findByDeliverTime(Instant DeliverTime) {

        return chefservice.findByDeliverTime( DeliverTime);
    }


    @GetMapping(value =APP_ROOT+"/chef/canteenworker")
    public ChefDto findByCanteenWorker(Canteenworker canteenworker) {

        return chefservice.findByCanteenworker(canteenworker);
    }
    @RequestMapping(value="/chefs", method= RequestMethod.GET)
    public List<ChefDto> findStaffDto(){
        return chefservice.findAll();
    }

    @DeleteMapping(value =APP_ROOT+"/order/delete/{id}" )
    public void delete(Integer id ) {

        chefservice.delete(id);
    }
}
