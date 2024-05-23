package com.robinfood.exceptions;

import com.robinfood.mappers.ResponseMapper;

public class BusinessRuleException extends ApplicationException {

    public BusinessRuleException(int httpStatusCode, String message) {
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
