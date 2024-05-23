package com.robinfood.app.usecases.roundvaluestransaction;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IRoundValuesTransactionUseCase {

    TransactionRequestDTO invoke(TransactionRequestDTO transactionRequest);
}
