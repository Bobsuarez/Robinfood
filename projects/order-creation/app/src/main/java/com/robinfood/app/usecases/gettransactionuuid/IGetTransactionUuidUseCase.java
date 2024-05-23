package com.robinfood.app.usecases.gettransactionuuid;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface IGetTransactionUuidUseCase {

    void invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
