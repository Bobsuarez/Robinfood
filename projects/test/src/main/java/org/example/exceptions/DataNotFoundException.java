package org.example.exceptions;

import org.example.dtos.response.ResponseDTO;

@SuppressWarnings("OverridableMethodCallInConstructor")
public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
