package com.robinfood.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationRequestException extends ResponseStatusException {

    public ValidationRequestException(final String message) {
        super(HttpStatus.PRECONDITION_FAILED, message);
    }
}
