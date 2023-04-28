package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.Repository.BillRepository;
import com.example.managingfoodreservation.Repository.DishRepository;
import com.example.managingfoodreservation.Repository.MenuCategoryRepository;
import com.example.managingfoodreservation.services.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private   MenuCategoryRepository menuCategoryRepository;
    @Autowired
    private    BillRepository billRepository;


    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
       Map<String,Object> map = new HashMap<>();
        map.put("MenuCategory",menuCategoryRepository.count());
        map.put("dish",dishRepository.count());
        map.put("bill",billRepository.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
