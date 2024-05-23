package com.robinfood.changestatusor.exceptions;

public class GenericChangeStatusOrException extends RuntimeException {

    public GenericChangeStatusOrException(String message) {
        super(message);
    }

    public GenericChangeStatusOrException(Exception exception) {
        super(exception.getMessage());
    }
}
