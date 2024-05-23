package com.robinfood.core.dtos.apiresponsebuilder;

import com.robinfood.core.enums.ExceptionEnum;

public class InternalServerErrorAbstractApiErrorResponseBuilderDTO<T> extends AbstractApiErrorResponseBuilderDTO<T> {

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
}
