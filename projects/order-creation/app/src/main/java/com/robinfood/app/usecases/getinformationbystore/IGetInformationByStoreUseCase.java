package com.robinfood.app.usecases.getinformationbystore;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface IGetInformationByStoreUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
