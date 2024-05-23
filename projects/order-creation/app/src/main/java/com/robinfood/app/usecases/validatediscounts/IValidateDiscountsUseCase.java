package com.robinfood.app.usecases.validatediscounts;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface IValidateDiscountsUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    );
}
