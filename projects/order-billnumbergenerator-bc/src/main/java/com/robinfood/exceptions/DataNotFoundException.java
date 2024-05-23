package com.robinfood.exceptions;

import com.robinfood.dtos.response.ResponseDTO;

@SuppressWarnings("OverridableMethodCallInConstructor")
public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }
}
