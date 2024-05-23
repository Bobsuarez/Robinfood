package com.robinfood.core.exceptions;

/**
 * Exception Cannot Change Order Status
 */
public class CannotChangeOrderStatusException extends Exception {

    public CannotChangeOrderStatusException(String errorMessage) {
        super(errorMessage);
    }
}
