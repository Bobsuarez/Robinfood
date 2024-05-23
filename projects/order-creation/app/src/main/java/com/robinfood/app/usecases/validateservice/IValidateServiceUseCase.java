package com.robinfood.app.usecases.validateservice;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

public interface IValidateServiceUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    );
}
