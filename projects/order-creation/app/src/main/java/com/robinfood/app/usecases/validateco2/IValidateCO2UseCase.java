package com.robinfood.app.usecases.validateco2;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface IValidateCO2UseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequestDTO
    );
}
