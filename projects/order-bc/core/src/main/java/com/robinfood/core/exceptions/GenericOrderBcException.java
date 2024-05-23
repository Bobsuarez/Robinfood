package com.robinfood.core.exceptions;

public class GenericOrderBcException extends RuntimeException {
    public GenericOrderBcException(String message) {
        super(message);
    }

    public GenericOrderBcException(Exception exception) {
        super(exception.getMessage());
    }

}
