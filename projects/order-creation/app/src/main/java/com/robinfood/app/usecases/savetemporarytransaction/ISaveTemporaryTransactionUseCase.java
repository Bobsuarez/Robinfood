package com.robinfood.app.usecases.savetemporarytransaction;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface ISaveTemporaryTransactionUseCase {

    /**
     * use case that stores transaction information in DynamoDB
     *
     * @param transactionRequestModified transaction request created
     */
    void invoke(TransactionRequestDTO transactionRequestModified);
}
