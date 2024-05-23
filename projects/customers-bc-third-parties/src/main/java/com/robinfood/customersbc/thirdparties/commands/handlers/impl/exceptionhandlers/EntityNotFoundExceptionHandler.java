package com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers;

import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.enums.ResponseCode;
import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import static com.robinfood.customersbc.thirdparties.constants.ExceptionTypeConstants.NOT_FOUND_TYPE;
import static com.robinfood.customersbc.thirdparties.utils.ResponseUtils.getErrorDTOMap;

public class EntityNotFoundExceptionHandler implements ExceptionHandler {

    @Override
    public ResponseDTO<Object> handleException(Throwable exception) {
        EntityNotFoundException ex = (EntityNotFoundException) exception;
        return ResponseDTO.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .message(HttpStatus.NOT_FOUND.getReasonPhrase())
            .error(getErrorDTOMap(NOT_FOUND_TYPE, ResponseCode.ENTITY_NOT_FOUND.getMessage(), ex.getMessage()))
            .build();
    }
}
