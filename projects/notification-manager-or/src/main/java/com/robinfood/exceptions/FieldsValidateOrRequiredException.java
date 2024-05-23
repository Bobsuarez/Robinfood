package com.robinfood.exceptions;

import com.robinfood.dtos.response.ResponseDTO;

public class FieldsValidateOrRequiredException extends ApplicationException {

    public FieldsValidateOrRequiredException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }

}
