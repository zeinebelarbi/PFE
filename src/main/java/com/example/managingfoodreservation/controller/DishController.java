package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.model.Dish;

import com.example.managingfoodreservation.wrapper.DishWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/dish")
public interface DishController {
    @PostMapping(path="/add")
    ResponseEntity<String> addNewDish(@RequestBody Map<String, String> requestMap);
    @GetMapping(path ="/get")
    ResponseEntity<List<DishWrapper>>getAllDish();
    @PostMapping(path = "/update ")
    ResponseEntity<String>updateDish(@RequestBody Map<String, String> requestMap);
    @PostMapping(path = "/delete/{iddish} ")
    ResponseEntity<String>deleteDish(@PathVariable Integer iddish);
    @PostMapping(path = "/updateStatus ")
    ResponseEntity<String>updateStatus(@RequestBody Map<String, String> requestMap);
    @GetMapping(path ="/getByMenuCategory/{id}")
    ResponseEntity<List<DishWrapper>>getByMenuCategory(@PathVariable Integer iddish);

    @GetMapping(path ="/getById/{id}")
    ResponseEntity<DishWrapper>getDishById(@PathVariable Integer iddish);

}
