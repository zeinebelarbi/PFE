package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.model.MenuCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path="/menucategory")
public interface MenuCategoryController {
    @PostMapping(path="/add")
    ResponseEntity<String> addNewMenuCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path ="/get")
    ResponseEntity<List<MenuCategory>>getAllMenuCategory(@RequestParam(required=false)
                                                         String filterValue);
    @PostMapping(path = "/update ")
    ResponseEntity<String>updateMenuCategory(@RequestBody(required = true) Map<String, String> requestMap);
}
