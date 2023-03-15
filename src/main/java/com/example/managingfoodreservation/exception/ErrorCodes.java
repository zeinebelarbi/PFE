package com.example.managingfoodreservation.exception;



public enum ErrorCodes {
    STAFF_NOT_FOUND(1000),
    STAFF_NOT_VALID(1001),
    STAFF_ALREADY_IN_USE(1002),
    DISH_NOT_FOUND(2000),
    DISH_NOT_VALID(2001),
    DISH_ALREADY_IN_USE(2002),
    MENU_NOT_FOUND(3000),
    MENU_NOT_VALID(3001),
   MENU_ALREADY_IN_USE(3002),
    ORDER_NOT_FOUND(4000),
    ORDER_NOT_VALID(4001),
    ORDER_ALREADY_IN_USE(4002),
    LISTOFORDERS_NOT_FOUND(5000),
    LISTOFORDERS_NOT_VALID(5001),
    LISTOFORDERS_ALREADY_IN_USE(5002),
    CHEF_NOT_FOUND(6000),
    CHEF_NOT_VALID(6001),
    CHEF_ALREADY_IN_USE(6002),
    CANTEENWORKER_NOT_FOUND(7000),
    CANTEENWORKER_NOT_VALID(7000),
    CANTEENWORKER_ALREADY_IN_USE(7002);

  
    private final int code ;

    ErrorCodes(int code) {
        this.code = code;

    }
    public int getCode() {
        return code;
    }
}
