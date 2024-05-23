package com.robinfood.localserver.commons.exceptions;

public class IncompleteDataException extends Exception {

    private final transient Object error;

    public IncompleteDataException(String message) {
        super(message);
        error = null;
    }

    public IncompleteDataException(String message, Object error) {
        super(message);
        this.error = error;
    }

    public Object getError() {
        return error;
    }
}
