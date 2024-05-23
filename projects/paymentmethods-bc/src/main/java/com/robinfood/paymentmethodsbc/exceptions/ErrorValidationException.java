package com.robinfood.paymentmethodsbc.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.robinfood.paymentmethodsbc.constants.MessageConstants.VALIDATE_MESSAGE_FORMAT;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String type;
    private final String message;

    public ErrorValidationException(String type, String message) {
        super(String.format(VALIDATE_MESSAGE_FORMAT, type, message));
        this.type = type;
        this.message = message;
    }
}
