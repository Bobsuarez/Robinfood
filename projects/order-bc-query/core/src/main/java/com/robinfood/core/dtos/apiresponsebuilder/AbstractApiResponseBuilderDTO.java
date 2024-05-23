package com.robinfood.core.dtos.apiresponsebuilder;

import com.robinfood.core.enums.ExceptionEnum;

/**
 * Abstract class that allows the creation of ApiResponseDTO objects
 */
public abstract class AbstractApiResponseBuilderDTO<T> {

    protected ApiResponseDTO<T> apiResponseDTO;

    protected ApiErrorResponseDTO<T> apiErrorResponseDTO;

    protected String message;

    /**
     * Returns the ApiResponseDTO object built
     * @return apiResponseDTO
     */
    public ApiResponseDTO<T> getApiResponseDTO() {
        return apiResponseDTO;
    }

    /**
     * Returns the ApiResponseDTO object built
     * @return apiErrorResponseDTO
     */
    public ApiErrorResponseDTO<T> getApiErrorResponseDTO() {
        return apiErrorResponseDTO;
    }

    /**
     * Builds the ApiResponseDTO object
     * @param data the data of the response
     */
    public abstract void build(T data);

    public abstract void build(String message);

    public void build(T errors, ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                exceptionEnum
        );
    }
}
