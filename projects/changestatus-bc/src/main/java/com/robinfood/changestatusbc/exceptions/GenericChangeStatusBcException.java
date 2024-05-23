package com.robinfood.changestatusbc.exceptions;

public class GenericChangeStatusBcException extends RuntimeException {

    public GenericChangeStatusBcException(String message) {
        super(message);
    }

    public GenericChangeStatusBcException(Exception exception) {
        super(exception.getMessage());
    }
}
