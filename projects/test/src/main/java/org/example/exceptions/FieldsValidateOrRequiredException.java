package org.example.exceptions;

import org.example.dtos.response.ResponseDTO;

public class FieldsValidateOrRequiredException extends ApplicationException {

    public FieldsValidateOrRequiredException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
