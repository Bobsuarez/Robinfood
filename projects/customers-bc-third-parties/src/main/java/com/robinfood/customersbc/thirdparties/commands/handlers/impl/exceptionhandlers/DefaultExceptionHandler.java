package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;

public class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        return ResponseDTO.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .build();
    }
}
