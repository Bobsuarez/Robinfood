package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.mappers.ResponseMapper;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;

public class DataBaseException extends ApplicationException {

    public DataBaseException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                        exception.getMessage(),
                        DEFAULT_BOOLEAN_TRUE), exception.getMessage());
    }

    public DataBaseException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
