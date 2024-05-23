package com.robinfood.core.dtos.apiresponsebuilder;

public class NotFoundAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {
    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
        apiResponseDTO.notFound();
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
        apiResponseDTO.notFound();
    }
}
