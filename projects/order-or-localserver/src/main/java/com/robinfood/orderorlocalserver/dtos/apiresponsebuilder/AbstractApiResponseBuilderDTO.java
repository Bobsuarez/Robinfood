package com.robinfood.orderorlocalserver.dtos.apiresponsebuilder;


import com.robinfood.orderorlocalserver.enums.ApiResponseEnum;
import com.robinfood.orderorlocalserver.enums.ExceptionEnum;

public abstract class AbstractApiResponseBuilderDTO<T> {

    protected APIResponseDTO<T> apiResponseDTO;

    protected ApiErrorResponseDTO<T> apiErrorResponseDTO;

    public APIResponseDTO<T> getApiResponseDTO() {
        return apiResponseDTO;
    }

    public ApiErrorResponseDTO<T> getApiErrorResponseDTO() {
        return apiErrorResponseDTO;
    }

    public abstract void build(T data, ApiResponseEnum apiResponseEnum);

    public void build(T errors, ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                exceptionEnum
        );
    }
}
