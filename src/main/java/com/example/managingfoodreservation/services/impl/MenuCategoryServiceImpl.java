package com.example.managingfoodreservation.services.impl;





import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.Repository.MenuCategoryRepository;
import com.example.managingfoodreservation.constants.MenuConstants;

import com.example.managingfoodreservation.model.MenuCategory;
import com.example.managingfoodreservation.services.MenuCategoryService;

import com.example.managingfoodreservation.utils.MenuUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public  class MenuCategoryServiceImpl implements MenuCategoryService {
    @Autowired
    MenuCategoryRepository menuCategoryRepository;
    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewMenuCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    menuCategoryRepository.save(getMenuCategoryFromMap(requestMap, false));
                    return MenuUtils.getResponseEntity("MenuCategory Added Successfully", HttpStatus.OK);
                }
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private MenuCategory getMenuCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
        MenuCategory menuCategory = new MenuCategory();
        if (isAdd) {
            menuCategory.setIdMenuCategory(Integer.parseInt(requestMap.get("id")));
        }
        menuCategory.setMenucategoryname(requestMap.get("name"));
        return menuCategory;

    }
    @Override
    public ResponseEntity<List<MenuCategory>> getAllMenuCategory(String filterValue) {
        try{
   if(!Strings.isNullOrEmpty(filterValue)&& filterValue.equalsIgnoreCase("true")){
   log.info("Inside if ");

    return new ResponseEntity<List<MenuCategory>>(menuCategoryRepository.getAllMenuCategory(),HttpStatus.OK);
}
   return new ResponseEntity(menuCategoryRepository.findAll(),HttpStatus.OK);
        }catch(Exception ex ){
ex.printStackTrace();
        }
return new ResponseEntity<List<MenuCategory>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateMenuCategory(Map<String, String> requestMap) {
        try{
if (jwtFilter.isAdmin()){
if (validateCategoryMap(requestMap, true)){
    Optional optional= menuCategoryRepository.findById(Integer.parseInt(requestMap.get("id")));
    if(!optional.isEmpty()){
menuCategoryRepository.save(getMenuCategoryFromMap(requestMap,true));
return MenuUtils.getResponseEntity("MenuCategory updated successfully",HttpStatus.OK);
    }else{
        return MenuUtils.getResponseEntity("MenuCategory id doesn't exist",HttpStatus.OK);
    }
}
return MenuUtils.getResponseEntity(MenuConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
}else{
   return   MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
}
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}



