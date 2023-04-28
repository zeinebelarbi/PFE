package com.example.managingfoodreservation.controller.impl;




import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.controller.MenuCategoryController;

import com.example.managingfoodreservation.model.MenuCategory;
import com.example.managingfoodreservation.services.MenuCategoryService;
import com.example.managingfoodreservation.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController

public class MenuCategoryControllerImpl implements MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryservice;

    @Override
    public ResponseEntity<String> addNewMenuCategory(Map<String, String> requestMap) {
       try{
       return menuCategoryservice.addNewMenuCategory(requestMap);
       }catch(Exception ex) {
           ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<MenuCategory>> getAllMenuCategory( String filterValue) {
       try{
return menuCategoryservice.getAllMenuCategory(filterValue);
       }catch(Exception ex){
           ex.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateMenuCategory(Map<String, String> requestMap) {
      try{
return menuCategoryservice.updateMenuCategory(requestMap);
      }catch(Exception ex){
          ex.printStackTrace();
      }
      return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
