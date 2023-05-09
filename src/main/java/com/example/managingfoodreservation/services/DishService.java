package com.example.managingfoodreservation.services;


import com.example.managingfoodreservation.wrapper.DishWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DishService {


    ResponseEntity<String> addNewDish(Map<String, String> requestMap);

    ResponseEntity<List<DishWrapper>> getAllDish(String filterValue);

    ResponseEntity<String> updateDish(Map<String, String> requestMap);

    ResponseEntity<String> deleteDish(Integer iddish);

    ResponseEntity<String> updateStatus(Map<String, String> requestMap);



    ResponseEntity<List<DishWrapper>> getByMenuCategory(Integer idMenuCategory);

    ResponseEntity<DishWrapper> getDishById(Integer iddish);
}
