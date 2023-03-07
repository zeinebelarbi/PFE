package com.example.managingfoodreservation.exception;



public enum ErrorCodes {
    STAFF_NOT_FOUND(1000),
    STAFF_NOT_VALID(1001),
    DISH_NOT_FOUND(2000),
    DISH_NOT_VALID(2001),
    MENU_NOT_FOUND(3000),
    MENU_NOT_VALID(3001),
    ORDER_NOT_FOUND(4000),
    ORDER_NOT_VALID(4001),
    LISTOFORDERS_NOT_FOUND(5000),
    LISTOFORDERS_NOT_VALID(5001),
    CHEF_NOT_FOUND(6000),
    CHEF_NOT_VALID(6001),
    CANTEENWORKER_NOT_FOUND(7000),
    CANTEENWORKER_NOT_VALID(7001);
  
    private final int code ;

    ErrorCodes(int code) {
        this.code = code;

    }
}
