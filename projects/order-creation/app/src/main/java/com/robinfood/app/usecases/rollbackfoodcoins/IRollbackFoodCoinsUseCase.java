package com.robinfood.app.usecases.rollbackfoodcoins;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface IRollbackFoodCoinsUseCase {

    CompletableFuture<Void> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    );
}
