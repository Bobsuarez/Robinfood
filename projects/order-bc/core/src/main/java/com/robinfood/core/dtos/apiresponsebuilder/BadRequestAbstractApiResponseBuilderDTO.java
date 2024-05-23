package com.robinfood.core.dtos.apiresponsebuilder;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class BadRequestAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
        apiResponseDTO.badRequest();
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
        apiResponseDTO.badRequest();
    }
}
