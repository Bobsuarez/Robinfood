package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.UNAUTHORIZED_TYPE;
import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOMap;

public class AuthenticationCredentialsNotFoundExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        AuthenticationCredentialsNotFoundException ex = (AuthenticationCredentialsNotFoundException) exception;

        return ResponseDTO.builder()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .error(getErrorDTOMap(UNAUTHORIZED_TYPE, ex.getClass().getSimpleName(), ex.getMessage()))
            .build();
    }
}
