package com.example.managingfoodreservation.controller;



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
    ResponseEntity<List<DishWrapper>> getAllDish(@RequestParam(value = "filterValue", required = false) String filterValue);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateDish(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{iddish}")
    ResponseEntity<String> deleteDish(@PathVariable Integer iddish);

    @PostMapping(path = "/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);

    @GetMapping(path ="/getByMenuCategory/{idMenuCategory}")
    ResponseEntity<List<DishWrapper>>   getDishsByMenucategory(@PathVariable("idMenuCategory") Integer idMenuCategory);

    @GetMapping(path ="/getById/{iddish}")
    ResponseEntity<DishWrapper> getDishById(@PathVariable Integer iddish);
}

