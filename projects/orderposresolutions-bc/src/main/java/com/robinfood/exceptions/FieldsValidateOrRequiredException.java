package com.robinfood.exceptions;

import com.robinfood.dtos.v1.response.ResponseDTO;

public class FieldsValidateOrRequiredException extends ApplicationException {

    public FieldsValidateOrRequiredException(ResponseDTO responseMapper, String message) {
        super(responseMapper, message);
    }
}
