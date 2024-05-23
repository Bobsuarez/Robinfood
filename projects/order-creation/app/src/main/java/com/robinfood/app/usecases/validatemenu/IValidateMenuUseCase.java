package com.robinfood.app.usecases.validatemenu;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface IValidateMenuUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    );
}
