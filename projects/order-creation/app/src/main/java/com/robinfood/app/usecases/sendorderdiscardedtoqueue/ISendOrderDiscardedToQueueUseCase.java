package com.robinfood.app.usecases.sendorderdiscardedtoqueue;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface ISendOrderDiscardedToQueueUseCase {

    TransactionRequestDTO invoke(TransactionRequestDTO transactionRequest);
}
