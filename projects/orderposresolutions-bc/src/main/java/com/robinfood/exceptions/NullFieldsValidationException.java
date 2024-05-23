package com.robinfood.exceptions;

import com.robinfood.dtos.v1.response.ResponseDTO;

public class NullFieldsValidationException extends ApplicationException {

    public NullFieldsValidationException(ResponseDTO responseMapper, String message) {
        super(responseMapper, message);
    }
}
