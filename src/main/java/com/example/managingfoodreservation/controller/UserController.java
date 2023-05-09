package com.example.managingfoodreservation.controller;


import com.example.managingfoodreservation.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping(path="/user")
@CrossOrigin("localhost:4200")

public interface UserController {
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();


    @PostMapping(path = "/update")
    public  ResponseEntity<String>update(@RequestBody(required = true)Map <String,String>requestMap);



    @GetMapping(path ="/checkToken")
    ResponseEntity<String>checkToken();


    @PostMapping(path ="/changePassword")
ResponseEntity<String>changePassword(@RequestBody Map<String,String> requestMap );
@PostMapping(path="/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> requestMap);
    @PostMapping(path="/delete/{id}")
    ResponseEntity<String>delete(@PathVariable Integer id);

}