package com.robinfood.app.usecases.gettransactionbyid;

import com.robinfood.core.dtos.TransactionFlowDTO;

public interface IGetTransactionFlowByIdUseCase {

    /**
     * Method that searches for a transaction by its id
     *
     * @param id    transaction id
     * @return {@link TransactionFlowDTO}
     */
    TransactionFlowDTO invoke(Long id);

}
