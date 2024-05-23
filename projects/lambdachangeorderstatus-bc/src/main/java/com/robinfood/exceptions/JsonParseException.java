package com.robinfood.exceptions;

import com.robinfood.dtos.response.ResponseDTO;

public class JsonParseException extends ApplicationException {

    public JsonParseException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);

    }
}
