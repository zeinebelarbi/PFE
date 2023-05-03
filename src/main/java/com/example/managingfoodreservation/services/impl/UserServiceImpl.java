package com.example.managingfoodreservation.services.impl;
import com.example.managingfoodreservation.JWT.CustomerUsersDetailsService;

import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.JWT.JwtUtil;
import com.example.managingfoodreservation.Repository.UserRepository;
import com.example.managingfoodreservation.constants.MenuConstants;



import com.example.managingfoodreservation.model.User;
import com.example.managingfoodreservation.services.UserService;

import com.example.managingfoodreservation.utils.EmailUtils;
import com.example.managingfoodreservation.utils.MenuUtils;
import com.example.managingfoodreservation.wrapper.UserWrapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    EmailUtils emailUtils;


    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        log.info("Inside signup{} ", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return MenuUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return MenuUtils.getResponseEntity("email already exists.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("username") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }

        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setUsername(requestMap.get("username"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setRole("user");
        user.setStatus("false");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(
                            customerUsersDetailsService.getUserDetail().getEmail(), customerUsersDetailsService.getUserDetail().getRole()) + "\"}"
                            , HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"" + "wait for admin approval." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }

            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad credentials." + "\"}",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userRepository.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userRepository.getAllAdmin());
                    return MenuUtils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);

                } else {
                    MenuUtils.getResponseEntity("User id doesn't exist", HttpStatus.OK);
                }
            } else {
                return MenuUtils.getResponseEntity(MenuConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER:-" + user + "\n is approved by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER:-" + user + "\n is disabled by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        }
    }

    @Override
    public ResponseEntity<String> checkToken() {


        return MenuUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User userObj = userRepository.findByEmail(jwtFilter.getCurrentUser());
            if (!userObj.equals(null)) {
                if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get(requestMap.get("newPassword")));
                    userRepository.save(userObj);
                    return MenuUtils.getResponseEntity("Password Updated Successfully ", HttpStatus.OK);
                }

                return MenuUtils.getResponseEntity("Incorrect Old Password ", HttpStatus.BAD_REQUEST);
            }

            return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
  User user =userRepository.findByEmail(requestMap.get("email"));
if(!Objects.isNull(user)&& !Strings.isNullOrEmpty(user.getEmail())){

emailUtils.forgotMail(user.getEmail(), "Credentials by Food management system", user.getPassword());
    return MenuUtils.getResponseEntity("check your mail for credentials.",HttpStatus.OK);
}
        } catch(Exception ex){
        ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}


