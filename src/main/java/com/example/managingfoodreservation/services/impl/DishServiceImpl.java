package com.example.managingfoodreservation.services.impl;


import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.Repository.DishRepository;


import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.model.MenuCategory;
import com.example.managingfoodreservation.services.DishService;

import com.example.managingfoodreservation.utils.MenuUtils;
import com.example.managingfoodreservation.wrapper.DishWrapper;
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


@Slf4j
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    JwtFilter jwtFilter;


    @Override
    public ResponseEntity<String> addNewDish(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateDishMap(requestMap, false)) {
                    dishRepository.save(getDishFromMap(requestMap, false));
                    return MenuUtils.getResponseEntity("Dish Added Successfully", HttpStatus.OK);
                }
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateDishMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {

            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Dish getDishFromMap(Map<String, String> requestMap, boolean isAdd) {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setIdMenuCategory(Integer.parseInt(requestMap.get("MenuCategoryId")));
        Dish dish = new Dish();
        if (isAdd) {
            dish.setIddish(Integer.parseInt(requestMap.get("DishId")));
        } else {
            dish.setStatus("true");
        }


        dish.setMenuCategory(menuCategory);
        dish.setDishname(requestMap.get("name"));
        dish.setDesription(requestMap.get("description"));
        dish.setPrice(Integer.parseInt(requestMap.get("Dishprice")));

        return dish;
    }

    @Override
    public ResponseEntity<List<DishWrapper>> getAllDish(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside if ");

                return new ResponseEntity<>(dishRepository.getAllDish(), HttpStatus.OK);
            }
            return new ResponseEntity(dishRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateDish(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {

                if (validateDishMap(requestMap, true)) {
                    Optional<Dish> optional = dishRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        Dish dish = getDishFromMap(requestMap, true);
                        dish.setStatus(optional.get().getStatus());
                        dishRepository.save(dish);
                        return MenuUtils.getResponseEntity("Dish updated successfully", HttpStatus.OK);
                    } else {
                        return MenuUtils.getResponseEntity("Dish  id doesn't exist", HttpStatus.OK);
                    }
                }
                return MenuUtils.getResponseEntity(MenuConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteDish(Integer iddish) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = dishRepository.findById(iddish);
                if (!optional.isEmpty()) {
                    dishRepository.deleteById(iddish);
                    return MenuUtils.getResponseEntity("Dish deleted Successfully ", HttpStatus.OK);
                }
                return MenuUtils.getResponseEntity("Dish Id does not exist ", HttpStatus.OK);
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = dishRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    dishRepository.updateDishStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return MenuUtils.getResponseEntity("Dish status Updated Successfuly", HttpStatus.OK);

                }
                return MenuUtils.getResponseEntity("Dish id does not exist", HttpStatus.OK);


            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<DishWrapper>> getByMenuCategory(Integer iddish) {
        try {
            return new ResponseEntity<>(dishRepository.getDishByMenuCategory(iddish), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<DishWrapper> getDishById(Integer iddish) {
        try {
return new ResponseEntity<>(dishRepository.getDishById(iddish),HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new DishWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


