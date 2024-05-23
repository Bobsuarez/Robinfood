package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.mappers.ResponseMapper;

public class JmsConnectionException extends ApplicationException {

    public JmsConnectionException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_CONFLICT.getCodeHttp(),
                        exception.getMessage(),
                        Boolean.TRUE), exception.getMessage());

    }
}
