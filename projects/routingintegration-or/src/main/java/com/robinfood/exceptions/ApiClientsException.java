package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.mappers.ResponseMapper;

public class ApiClientsException extends ApplicationException {

    public ApiClientsException(String inMessage) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                        inMessage,
                        Boolean.TRUE), inMessage);
    }

    public ApiClientsException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_CONFLICT.getCodeHttp(),
                        exception.getMessage(),
                        Boolean.TRUE), exception.getMessage());

    }
}
