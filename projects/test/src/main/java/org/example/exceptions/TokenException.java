package org.example.exceptions;

import org.apache.http.HttpStatus;
import org.example.mappers.ResponseMapper;

public class TokenException extends ApplicationException {

    public TokenException(String inMessage) {
        super(ResponseMapper
                .buildWithError(HttpStatus.SC_UNAUTHORIZED,
                        inMessage,
                        true), inMessage);
    }
}
