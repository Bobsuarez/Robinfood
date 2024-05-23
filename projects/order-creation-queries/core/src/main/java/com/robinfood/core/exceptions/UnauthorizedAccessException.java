package com.robinfood.core.exceptions;

public class UnauthorizedAccessException extends Exception {

    public UnauthorizedAccessException(String errorMessage) {
        super(errorMessage);
    }
}
