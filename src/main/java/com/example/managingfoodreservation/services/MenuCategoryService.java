package com.example.managingfoodreservation.services;


import com.example.managingfoodreservation.model.MenuCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MenuCategoryService {
    ResponseEntity<String> addNewMenuCategory(Map<String,String> requestMap);

    ResponseEntity<List<MenuCategory>> getAllMenuCategory(String filterValue);
    ResponseEntity<String>updateMenuCategory(Map<String,String>requestMap);

}
