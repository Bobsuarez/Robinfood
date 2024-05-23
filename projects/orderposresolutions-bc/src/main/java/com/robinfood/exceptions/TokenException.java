package com.robinfood.exceptions;

import com.robinfood.mappers.ResponseMapper;
import org.apache.http.HttpStatus;

public class TokenException extends ApplicationException {

    public TokenException(String message) {
        super(ResponseMapper
                .buildWithError(HttpStatus.SC_UNAUTHORIZED,
                        message,
                        Boolean.TRUE
                ), message);
    }
}
