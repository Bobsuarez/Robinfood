package com.robinfood.configurations.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends Exception {

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(HttpStatus httpStatus, String message) {
        super(message);
    }
}