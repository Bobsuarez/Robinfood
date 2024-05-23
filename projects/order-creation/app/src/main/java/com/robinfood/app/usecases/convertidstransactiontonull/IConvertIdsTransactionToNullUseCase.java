package com.robinfood.app.usecases.convertidstransactiontonull;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IConvertIdsTransactionToNullUseCase {

    void invoke(TransactionRequestDTO transactionRequestDTO);
}
