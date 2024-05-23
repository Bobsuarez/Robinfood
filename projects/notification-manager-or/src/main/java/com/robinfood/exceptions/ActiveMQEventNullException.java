package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.mappers.ResponseMapper;

public class ActiveMQEventNullException extends ApplicationException {

    public ActiveMQEventNullException(String inMessage) {
        super(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                        inMessage,
                        Boolean.TRUE), inMessage);
    }
}
