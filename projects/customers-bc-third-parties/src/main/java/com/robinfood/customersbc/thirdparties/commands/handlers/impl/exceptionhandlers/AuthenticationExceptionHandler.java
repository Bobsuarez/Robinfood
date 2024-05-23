package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.UNAUTHORIZED_TYPE;

public class AuthenticationExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        AuthenticationException ex = (AuthenticationException) exception;

        return ResponseDTO.builder()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .error(ResponseUtils.getErrorDTOMap(UNAUTHORIZED_TYPE, ex.getClass().getSimpleName(), ex.getMessage()))
            .build();
    }
}
