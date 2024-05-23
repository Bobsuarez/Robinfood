package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;

public abstract class AbstractApiResponseBuilderDTO<T> {

    protected APIResponseDTO<T> apiResponseDTO;

    protected ApiErrorResponseDTO<T> apiErrorResponseDTO;

    /**
     * Returns the ApiResponseDTO object built
     * @return apiResponseDTO
     */
    public APIResponseDTO<T> getApiResponseDTO() {
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
     * @param apiResponseEnum the data of the response
     */
    public abstract void build(T data, ApiResponseEnum apiResponseEnum);

    public void build(T errors, ExceptionEnum exceptionEnum) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                exceptionEnum
        );
    }

    public void build(T errors, String message, HttpStatus httpStatus) {
        apiErrorResponseDTO = new ApiErrorResponseDTO<>(
                errors,
                message,
                httpStatus
        );
    }

}
