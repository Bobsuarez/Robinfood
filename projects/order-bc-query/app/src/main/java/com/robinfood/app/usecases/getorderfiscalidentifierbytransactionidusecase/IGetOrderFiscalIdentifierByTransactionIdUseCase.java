package com.robinfood.app.usecases.getorderfiscalidentifierbytransactionidusecase;

import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;

public interface IGetOrderFiscalIdentifierByTransactionIdUseCase {

    /**
     *  Get the fiscal identifier of an order
     * @param transactionId the transaction identifier
     * @return the order fiscal identifier by transactionId
     */
    OrderFiscalIdentifierDTO invoke(Long transactionId);
}
