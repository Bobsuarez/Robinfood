package com.robinfood.core.exceptions;

/**
 * Extends of Exception
 */
public class CannotDivideByZeroException extends Exception {

    /**
     * Validate that the divisors of a division are not zero
     * @param errorMessage Error message of the exception
     */
    public CannotDivideByZeroException(String errorMessage) {
        super(errorMessage);
    }
}
