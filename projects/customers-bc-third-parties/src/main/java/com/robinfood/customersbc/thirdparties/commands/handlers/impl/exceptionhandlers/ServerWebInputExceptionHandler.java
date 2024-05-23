package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import org.springframework.web.server.ServerWebInputException;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.SERVER_WEB_INPUT_TYPE;
import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOMap;

public class ServerWebInputExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        ServerWebInputException ex = (ServerWebInputException) exception;

        return ResponseDTO.builder()
            .code(ResponseCode.CLIENT_ERROR.getCode())
            .message(ResponseCode.CLIENT_ERROR.getMessage())
            .error(getErrorDTOMap(SERVER_WEB_INPUT_TYPE, ex.getClass().getSimpleName(), ex.getMessage()))
            .build();
    }
}
