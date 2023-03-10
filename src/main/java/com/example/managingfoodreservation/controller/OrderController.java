package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.dto.OrderDto;
import com.example.managingfoodreservation.model.Staff;
import com.example.managingfoodreservation.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

@RestController
@RequestMapping("/order")
public abstract class OrderController {

        private OrderService orderservice;
        @Autowired
        public OrderController(OrderService orderservice){
        this.orderservice=orderservice;
        }
    @PostMapping(value =APP_ROOT+ "/order/create")
    public OrderDto save(@RequestBody OrderDto dto) {

        return orderservice.save(dto);
    }


    @GetMapping(value =APP_ROOT+"/order/{id_User}")
    public OrderDto findByStaff(Staff staff, @PathVariable String id_User) {

        return orderservice.findByStaff(staff);
    }

    @GetMapping(value =APP_ROOT+"/order/id")
    public OrderDto findById(Integer id) {

        return orderservice.findById(id);
    }
    @GetMapping(value =APP_ROOT+"/order/name")
    public OrderDto findByName(String name) {

        return orderservice.findByName(name);
    }
    @GetMapping(value =APP_ROOT+"/order/number")
    public OrderDto findByNumber(Integer number) {

        return orderservice.findByNumber(number);
    }
    @GetMapping(value =APP_ROOT+"/order/date")
    public OrderDto findByDate(Instant date) {

        return orderservice.findByDate(date);
    }


    @DeleteMapping(value =APP_ROOT+"/order/delete/{id}" )
    public void delete(Integer id) {

       orderservice.delete(id);
    }
}
