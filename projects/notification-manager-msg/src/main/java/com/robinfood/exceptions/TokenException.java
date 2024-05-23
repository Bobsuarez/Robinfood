package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.mappers.ResponseMapper;

import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_TRUE;

public class TokenException extends ApplicationException {

    public TokenException(String inMessage) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_UNAUTHORIZED.getCodeHttp(),
                        inMessage,
                        DEFAULT_BOOLEAN_TRUE), inMessage);
    }
}
