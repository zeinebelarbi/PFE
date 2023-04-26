package com.example.managingfoodreservation.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path ="/dashbord")
public interface DashboardController {
    @GetMapping(path="/details")
    ResponseEntity<Map<String,Object>> getCount();
}
