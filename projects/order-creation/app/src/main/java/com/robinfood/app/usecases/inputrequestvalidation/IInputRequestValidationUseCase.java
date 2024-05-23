package com.robinfood.app.usecases.inputrequestvalidation;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface IInputRequestValidationUseCase {

    void invoke(@NonNull TransactionRequestDTO transactionRequest);
}
