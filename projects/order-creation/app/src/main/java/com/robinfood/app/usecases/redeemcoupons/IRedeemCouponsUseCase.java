package com.robinfood.app.usecases.redeemcoupons;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface IRedeemCouponsUseCase {

    CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
