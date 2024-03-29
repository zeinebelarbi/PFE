package com.example.managingfoodreservation.services.impl;


import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.Repository.DishRepository;


import com.example.managingfoodreservation.Repository.MenuCategoryRepository;
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

import javax.persistence.EntityManager;
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
    @Autowired
    EntityManager entityManager;
    @Autowired
    MenuCategoryRepository menuCategoryRepository;
    private boolean validateDishMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("dishname")) {

            if (requestMap.containsKey("iddish") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<String> addNewDish(Map<String, String> requestMap) {
        try {
            if (validateDishMap(requestMap, false)) {
                dishRepository.save(getDishFromMap(requestMap, false));
                return MenuUtils.getResponseEntity("Dish Added Successfully", HttpStatus.OK);
            }
            else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }




    private Dish getDishFromMap(Map<String, String> requestMap, boolean isAdd) {
        MenuCategory menuCategory = new MenuCategory();
        String menuCategoryId = requestMap.get("MenuCategoryId");
        if (menuCategoryId != null && !menuCategoryId.isEmpty()) {
            menuCategory.setIdMenuCategory(Integer.parseInt(menuCategoryId));
        }
        Dish dish = new Dish();
        String dishIdStr = requestMap.get("iddish");
        if (dishIdStr != null && !dishIdStr.isEmpty()) {
            dish.setIddish(Integer.parseInt(dishIdStr));
        } else {
            dish.setStatus("true");
        }
        dish.setMenuCategory(menuCategory);
        dish.setDishname(requestMap.get("dishname"));
        dish.setDescription(requestMap.get("description"));
        dish.setPrice(Float.valueOf(requestMap.get("price")));
        return dish;
    }

    @Override
    public ResponseEntity<List<DishWrapper>> getAllDish(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside if ");
                return new ResponseEntity<>(dishRepository.getAllDish(), HttpStatus.OK);
            }

            List<DishWrapper> dishes = entityManager.createNamedQuery("Dish.getAllDish", DishWrapper.class).getResultList();
            return new ResponseEntity(dishes, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> updateDish(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateDishMap(requestMap, true) && requestMap.containsKey("MenuCategoryId")) {
                    Optional<Dish> optional = dishRepository.findById(Integer.parseInt(requestMap.get("iddish")));
                    if (!optional.isEmpty()) {
                        if (menuCategoryRepository.existsById(Integer.parseInt(requestMap.get("MenuCategoryId")))) {
                            Dish dish = getDishFromMap(requestMap, true);
                            dish.setStatus(optional.get().getStatus());
                            dishRepository.save(dish);
                            return MenuUtils.getResponseEntity("Dish updated successfully", HttpStatus.OK);
                        } else {
                            return MenuUtils.getResponseEntity("Menu Category id doesn't exist", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return MenuUtils.getResponseEntity("Dish id doesn't exist", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return MenuUtils.getResponseEntity(MenuConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
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
                    return MenuUtils.getResponseEntity("Dish deleted successfully", HttpStatus.OK);
                }
                return MenuUtils.getResponseEntity("Dish id doesn't exist", HttpStatus.BAD_REQUEST);
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
                Optional optional = dishRepository.findById(Integer.parseInt(requestMap.get("iddish")));
                if (!optional.isEmpty()) {
                    dishRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("iddish")));
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
    public ResponseEntity<List<DishWrapper>> getByMenuCategory(Integer idMenuCategory) {
        try {
            return new ResponseEntity<>(dishRepository.getDishByMenuCategory(idMenuCategory), HttpStatus.OK);
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


