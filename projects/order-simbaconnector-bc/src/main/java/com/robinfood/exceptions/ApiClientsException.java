package com.robinfood.exceptions;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.mappers.ResponseMapper;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_SEND_CONNECTOR_SIMBA;

public class ApiClientsException extends ApplicationException {

    public ApiClientsException(ResponseDTO responseMapper, String inMessage) {
        super(responseMapper, inMessage);
    }

    public ApiClientsException(String inMessage, Integer httpCode) {
        super(ResponseMapper
                .buildWithError(httpCode,
                        inMessage,
                        Boolean.TRUE), inMessage);
    }

    public ApiClientsException(Exception exception) {
        super(ResponseMapper
                        .buildWithError(HttpStatusConstants.SC_BAD_REQUEST.getCodeHttp(),
                                ERROR_PROCESS_SEND_CONNECTOR_SIMBA
                                        .replaceComplement(exception.getMessage()),
                                DEFAULT_BOOLEAN_TRUE),
                ERROR_PROCESS_SEND_CONNECTOR_SIMBA
                        .replaceComplement(exception.getMessage()));

    }
}
