package com.example.managingfoodreservation.controller.impl;
import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.controller.UserController;
import com.example.managingfoodreservation.services.UserService;
import com.example.managingfoodreservation.utils.MenuUtils;
import com.example.managingfoodreservation.wrapper.DishWrapper;
import com.example.managingfoodreservation.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


@RestController

public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userservice;

    @Autowired
    public UserControllerImpl(UserService staffservice) {

        this.userservice = staffservice;
    }


    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        try {
            return userservice.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userservice.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            return userservice.getAllUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            return userservice.update(requestMap);
        } catch
        (Exception ex) {
            ex.printStackTrace();
            {

            }
            return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<String> checkToken() {
     try{
return userservice.checkToken();
     }catch  (Exception ex){
         ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            return userservice.changePassword(requestMap);
        }catch  (Exception ex){
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
        try{
          return userservice.forgotPassword(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
          return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}

