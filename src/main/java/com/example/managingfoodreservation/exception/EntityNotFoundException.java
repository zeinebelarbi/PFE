package com.example.managingfoodreservation.exception;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;

    public EntityNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode=errorCode;
    }

    public EntityNotFoundException(String message, com.example.managingfoodreservation.exception.ErrorCodes canteenworkerNotFound) {
    }
}

