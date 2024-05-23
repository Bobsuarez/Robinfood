package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.robinfood.storeor.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;

public class ManagementErrorAbstractApiErrorResponseBuilderDTO<T>
        extends AbstractApiErrorResponseBuilderDTO<T> {

    @Override
    public void build(T errors, ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                exceptionEnum
        );
    }

    @Override
    public void build(ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                exceptionEnum
        );
    }

    @Override
    public void build(Integer code, String message, ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                code,
                message,
                exceptionEnum
        );
    }

    @Override
    public void build(T errors, String message, HttpStatus httpStatus) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                message,
                httpStatus
        );
    }

}
