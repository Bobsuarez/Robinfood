package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.mappers.ResponseDTOMapper;

public class TokenException extends ApplicationException {

    public TokenException(String inMessage) {
        super(ResponseDTOMapper
                .buildWithError(HttpStatusConstant.SC_UNAUTHORIZED.getCodeHttp(),
                        inMessage,
                        Boolean.TRUE), inMessage);
    }
}
