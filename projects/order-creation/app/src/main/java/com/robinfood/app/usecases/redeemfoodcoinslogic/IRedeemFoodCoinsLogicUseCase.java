package com.robinfood.app.usecases.redeemfoodcoinslogic;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import org.jetbrains.annotations.NotNull;

public interface IRedeemFoodCoinsLogicUseCase {

    TransactionCreationResult invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest,
            @NotNull Integer operationType
    );
}
