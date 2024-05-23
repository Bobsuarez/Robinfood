package com.robinfood.localorderbc.dtos.apiresponsebuilder;

import com.robinfood.localorderbc.enums.ExceptionEnum;

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

}
