package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.robinfood.storeor.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;

/**
 * Abstract class that allows the creation of ApiErrorResponseDTO objects
 */
public abstract class AbstractApiErrorResponseBuilderDTO<T> {

    protected ApiErrorResponseDTO<T> apiErrorResponseDTO;

    protected String message;

    /**
     * Returns the ApiResponseDTO object built
     * @return apiErrorResponseDTO
     */
    public ApiErrorResponseDTO<T> getResponse() {
        return apiErrorResponseDTO;
    }

    /**
     * Builds the ApiErrorResponseDTO object
     * @param errors the errors of the response
     */
    public abstract void build(T errors, ExceptionEnum exceptionEnum);

    public abstract void build(ExceptionEnum exceptionEnum);

    public abstract void build(Integer code, String message, ExceptionEnum exceptionEnum);

    public abstract void build(T errors, String message, HttpStatus httpStatus);

}
