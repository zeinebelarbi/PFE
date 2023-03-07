package com.example.managingfoodreservation.controller.Api;

import com.example.managingfoodreservation.dto.CanteenworkerDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;

public interface CanteenworkerApi {
    @PostMapping(value =APP_ROOT+ "/canteenworker/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CanteenworkerDto save(@RequestBody CanteenworkerDto dto);
    @GetMapping(value =APP_ROOT+"/canteenworker/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    CanteenworkerDto findByName(String name);
    @GetMapping(value =APP_ROOT+"/canteenworker/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CanteenworkerDto findById(@PathVariable("id") Integer id);
    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderPrice}",produces = MediaType.APPLICATION_JSON_VALUE)
    CanteenworkerDto findByOrderPrice(@PathVariable("OrderPrice") BigDecimal OrderPrice);

    CanteenworkerDto findByOrderPrice(Double OrderPrice);

    @GetMapping(value =APP_ROOT+"/canteenworker/{OrderTime}",produces = MediaType.APPLICATION_JSON_VALUE)
    CanteenworkerDto findByOrderTime(@PathVariable("OrderTime")Instant OrderTime);
    @GetMapping(value =APP_ROOT+"/canteenworker/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CanteenworkerDto> findAll();
    @DeleteMapping(value =APP_ROOT+"/canteenworker/delete/{id}" )
    void delete (@PathVariable("id") Integer id);
}
