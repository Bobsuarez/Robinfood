package com.robinfood.core.dtos.apiresponsebuilder;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class OkApiResponseDTO<T> extends AbstractBuilderApiResponseDTO<T> {

    /** -----------------------------------------PUBLIC METHODS------------------------------------ */

    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
    }
}
