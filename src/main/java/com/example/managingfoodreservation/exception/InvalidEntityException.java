package com.example.managingfoodreservation.exception;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException{
    @Getter
private ErrorCodes errorCode;
    @Getter
private List <String>errors;

    public InvalidEntityException(String message,Throwable cause,ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode=errorCode;
        this.errors=errors;
    }



    public InvalidEntityException(String theCanteenworkerIsNotValid, com.example.managingfoodreservation.exception.ErrorCodes canteenworkerNotValid, List<String> errors) {
    }
}
