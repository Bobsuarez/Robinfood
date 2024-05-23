package com.robinfood.app.usecases.saveinputrequest;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.jetbrains.annotations.NotNull;

public interface ISaveInputRequestUseCase {

    void invoke(@NotNull TransactionRequestDTO transactionRequest);
}
