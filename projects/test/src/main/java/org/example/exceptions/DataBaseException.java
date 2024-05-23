package org.example.exceptions;

import org.apache.http.HttpStatus;
import org.example.dtos.response.ResponseDTO;
import org.example.mappers.ResponseMapper;

public class DataBaseException extends ApplicationException {

    public DataBaseException(Exception exception) {
        super(ResponseMapper
                .buildWithError(HttpStatus.SC_NOT_FOUND,
                        exception.getMessage(),
                        true), exception.getMessage());
    }

    public DataBaseException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
