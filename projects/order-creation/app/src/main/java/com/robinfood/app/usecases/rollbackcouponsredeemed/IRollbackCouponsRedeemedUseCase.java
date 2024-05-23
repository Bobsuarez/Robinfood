package com.robinfood.app.usecases.rollbackcouponsredeemed;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.jetbrains.annotations.NotNull;

public interface IRollbackCouponsRedeemedUseCase {

    Object invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    );
}
