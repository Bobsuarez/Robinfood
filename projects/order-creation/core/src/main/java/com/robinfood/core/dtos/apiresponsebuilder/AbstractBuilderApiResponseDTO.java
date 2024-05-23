package com.robinfood.core.dtos.apiresponsebuilder;

/**
 * Abstract class that allows the creation of ApiResponseDTO objects
 */
public abstract class AbstractBuilderApiResponseDTO<T> {

    protected ApiResponseDTO<T> apiResponseDTO;

    protected String message;

    /**
     * Returns the ApiResponseDTO object built
     * @return apiResponseDTO
     */
    public ApiResponseDTO<T> getApiResponseDTO() {
        return apiResponseDTO;
    }

    /**
     * Builds the ApiResponseDTO object
     * @param data the data of the response
     */
    public abstract void build(T data);

    public abstract void build(String message);

}
