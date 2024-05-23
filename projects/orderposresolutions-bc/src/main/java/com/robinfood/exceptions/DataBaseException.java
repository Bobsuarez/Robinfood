package com.robinfood.exceptions;

import com.robinfood.dtos.v1.response.ResponseDTO;
import com.robinfood.mappers.ResponseMapper;
import org.apache.http.HttpStatus;

public class DataBaseException extends ApplicationException {

    public DataBaseException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatus.SC_NOT_FOUND,
                        exception.getMessage(),
                        Boolean.TRUE
                ), exception.getMessage());
    }

    public DataBaseException(ResponseDTO responseMapper, String message) {
        super(responseMapper, message);
    }
}
