package com.example.managingfoodreservation.controller.impl;

import com.example.managingfoodreservation.controller.DashboardController;
import com.example.managingfoodreservation.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class DashboardControllerImpl implements DashboardController {

    @Autowired
    DashboardService dashboardService;
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboardService.getCount();
    }
}
