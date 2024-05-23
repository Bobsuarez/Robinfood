package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.exceptions.JsonConversionException;
import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import org.springframework.http.HttpStatus;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.JSON_TYPE;
import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOMap;

public class JsonConversionExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        JsonConversionException ex = (JsonConversionException) exception;
        return ResponseDTO.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .error(getErrorDTOMap(JSON_TYPE, ResponseCode.JSON_MAPPING_ERROR.getMessage(), ex.getMessage()))
            .build();
    }
}
