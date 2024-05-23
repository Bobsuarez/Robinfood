package com.robinfood.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RollbackException extends ResponseStatusException {

    public RollbackException(final HttpStatus httpStatus, final String message) {
        super(httpStatus, message);
    }
}
