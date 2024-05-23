package com.robinfood.exceptions;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.mappers.ResponseMapper;

public class JsonParseException extends ApplicationException {

    public JsonParseException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);

    }

    public JsonParseException(Exception ex) {
        super(ResponseMapper
                .buildWithError(
                        HttpStatusConstant.SC_INTERNAL_SERVER_ERROR.getCodeHttp(),
                        ex.getMessage(),
                        GeneralConstants.DEFAULT_BOOLEAN_TRUE), ex.getMessage());

    }
}
