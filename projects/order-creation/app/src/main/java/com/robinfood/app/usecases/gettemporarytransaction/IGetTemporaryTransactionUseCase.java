package com.robinfood.app.usecases.gettemporarytransaction;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IGetTemporaryTransactionUseCase {

    /**
     * Method that invokes the use case to obtain the request from database temporal
     * @param transactionUuid uuid of the transactions
     * @return TransactionRequest that need to be process
     */
    TransactionRequestDTO invoke(String transactionUuid);
}
