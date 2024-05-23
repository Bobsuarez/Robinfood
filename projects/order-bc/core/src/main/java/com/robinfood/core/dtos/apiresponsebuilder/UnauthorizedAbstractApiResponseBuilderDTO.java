package com.robinfood.core.dtos.apiresponsebuilder;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class UnauthorizedAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    /** -----------------------------------------PUBLIC METHODS------------------------------------ */

    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
        apiResponseDTO.unauthorized();
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
        apiResponseDTO.unauthorized();
    }
}
