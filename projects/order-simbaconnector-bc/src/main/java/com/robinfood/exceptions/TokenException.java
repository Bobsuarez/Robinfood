package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.mappers.ResponseMapper;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;

public class TokenException extends ApplicationException {

    public TokenException(String inMessage) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstants.SC_UNAUTHORIZED.getCodeHttp(),
                        inMessage,
                        DEFAULT_BOOLEAN_TRUE), inMessage);
    }
}
