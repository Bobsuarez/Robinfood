package com.robinfood.exceptions;

import com.robinfood.dtos.v1.response.ResponseDTO;

@SuppressWarnings("OverridableMethodCallInConstructor")
public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException(ResponseDTO responseMapper, String message) {
        super(responseMapper, message);
    }

}
