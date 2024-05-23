package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.utils.ResponseUtils;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.SIGNATURE_TYPE;

public class SignatureExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        SignatureException ex = (SignatureException) exception;

        return ResponseDTO.builder()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .error(ResponseUtils.getErrorDTOMap(SIGNATURE_TYPE, ex.getClass().getSimpleName(), ex.getMessage()))
            .build();
    }
}
