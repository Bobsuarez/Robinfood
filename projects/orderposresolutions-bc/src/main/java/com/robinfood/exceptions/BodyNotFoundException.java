package com.robinfood.exceptions;

import com.robinfood.mappers.ResponseMapper;

@SuppressWarnings("OverridableMethodCallInConstructor")
public class BodyNotFoundException extends ApplicationException {

    public BodyNotFoundException(int httpStatusCode, String message) {
        super(
                ResponseMapper.buildWithError(
                        httpStatusCode,
                        message,
                        Boolean.TRUE
                ),
                message
        );
    }
}
