package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.mappers.ResponseMapper;

public class ApiClientsException extends ApplicationException {

    public ApiClientsException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }

    public ApiClientsException(String inMessage, Integer httpCode) {
        super(ResponseMapper
                .buildWithError(httpCode,
                        inMessage,
                        Boolean.TRUE), inMessage);
    }

    public ApiClientsException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstants.SC_BAD_REQUEST.getCodeHttp(),
                        exception.getMessage(),
                        Boolean.TRUE), exception.getMessage());

    }
}
