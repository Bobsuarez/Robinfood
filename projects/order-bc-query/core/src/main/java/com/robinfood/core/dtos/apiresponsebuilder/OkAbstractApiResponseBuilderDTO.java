package com.robinfood.core.dtos.apiresponsebuilder;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class OkAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
    }
}
