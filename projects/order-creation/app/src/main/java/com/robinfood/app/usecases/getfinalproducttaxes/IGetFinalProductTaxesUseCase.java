package com.robinfood.app.usecases.getfinalproducttaxes;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface IGetFinalProductTaxesUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
