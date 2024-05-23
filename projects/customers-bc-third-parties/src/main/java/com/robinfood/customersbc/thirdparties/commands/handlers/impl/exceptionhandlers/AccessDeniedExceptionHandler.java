package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOMap;

public class AccessDeniedExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        AccessDeniedException ex = (AccessDeniedException) exception;

        return ResponseDTO.builder()
            .code(HttpStatus.FORBIDDEN.value())
            .message(HttpStatus.FORBIDDEN.getReasonPhrase())
            .error(
                getErrorDTOMap(ExceptionTypeConstants.FORBIDDEN_TYPE, ex.getClass().getSimpleName(), ex.getMessage())
            )
            .build();
    }
}
