package com.robinfood.core.dtos.apiresponsebuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Builder implementation for a failed response
 */
@RequiredArgsConstructor
public class TransactionCreationFailedApiResponseDTO<T> extends AbstractBuilderApiResponseDTO<T> {

    private final int code;
    private final HttpStatus status;

    @Override
    public void build(T data) {
        apiResponseDTO = new ApiResponseDTO<>(data);
        apiResponseDTO.transactionCreationFailed(code, status);
    }

    @Override
    public void build(String message) {
        apiResponseDTO = new ApiResponseDTO<>(message);
        apiResponseDTO.preconditionFailed();
    }
}
