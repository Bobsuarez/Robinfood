package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.WEB_EXCHANGE_BIND_TYPE;
import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOFromMap;

@Slf4j
public class WebExchangeBindExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        final WebExchangeBindException ex = (WebExchangeBindException) exception;

        log.info("WebExchangeBindException errors: {}", ex.getFieldErrors());

        final Map<String, String> errors = ex.getFieldErrors().stream()
            .collect(Collectors.toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage
                )
            );

        log.info("WebExchangeBindException handled errors: {}", errors);

        return ResponseDTO.builder()
            .code(ex.getStatusCode().value())
            .message(ResponseCode.CLIENT_ERROR.getMessage())
            .error(getErrorDTOFromMap(WEB_EXCHANGE_BIND_TYPE, errors))
            .build();
    }
}
