package com.robinfood.core.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.core.enums.TransactionCreationErrors;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
@Setter
public class ErrorDataResponseDTO<T> {

    @Schema(example = "6", description = "The internal error code")
    private final int errorCode;

    @Schema(example = "'errorData': {}", description = "The content of the error data")
    private final T errorData;

    @Schema(example = "Error", description = "The message of the error data")
    private final String errorMessage;

    @Schema(example = "INCONSISTENT_TRANSACTION_ERROR", description = "The internal order creation error")
    private final TransactionCreationErrors transactionError;

}
