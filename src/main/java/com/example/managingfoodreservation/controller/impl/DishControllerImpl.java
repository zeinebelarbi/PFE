package com.example.managingfoodreservation.controller.impl;


import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.controller.DishController;


import com.example.managingfoodreservation.services.DishService;

import com.example.managingfoodreservation.utils.MenuUtils;
import com.example.managingfoodreservation.wrapper.DishWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController

public class DishControllerImpl implements DishController {
    @Autowired
    DishService dishService;


    @Override
    public ResponseEntity<String> addNewDish(Map<String,String> requestMap) {
        try {
            return dishService.addNewDish(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<DishWrapper>> getAllDish(@RequestParam(value = "filterValue", required = false) String filterValue) {
        try {

            return dishService.getAllDish(filterValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateDish(Map<String, String> requestMap) {
        try {
            return dishService.updateDish(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteDish(Integer iddish) {
        try {
            return dishService.deleteDish(iddish);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
return dishService.updateStatus(requestMap);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<DishWrapper>>   getDishsByMenucategory(Integer idMenuCategory) {
        try{
            return dishService. getByMenuCategory(idMenuCategory);
        }catch(Exception ex){

            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @Override
    public ResponseEntity<DishWrapper> getDishById(Integer iddish) {
        try{
   return dishService.getDishById(iddish);
        }catch(Exception ex){

            ex.printStackTrace();
        }
   return new ResponseEntity<>(new DishWrapper(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


