package com.robinfood.exceptions;

import com.robinfood.dtos.response.ResponseDTO;

public class FieldsValidateTypeException extends FieldsValidateOrRequiredException {

    public FieldsValidateTypeException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
