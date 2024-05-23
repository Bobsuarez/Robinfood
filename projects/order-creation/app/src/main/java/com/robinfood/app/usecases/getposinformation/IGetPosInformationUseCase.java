package com.robinfood.app.usecases.getposinformation;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface IGetPosInformationUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
