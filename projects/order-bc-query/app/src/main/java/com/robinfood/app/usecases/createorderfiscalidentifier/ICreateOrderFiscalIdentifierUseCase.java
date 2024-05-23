package com.robinfood.app.usecases.createorderfiscalidentifier;

import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;

import java.util.concurrent.CompletableFuture;

public interface ICreateOrderFiscalIdentifierUseCase {

    /**
     * Save the fiscal identifier of an order
     * @param orderFiscalIdentifierDTO the contract that obtains the fiscal identifier
     * @param transactionId the transaction identifier
     * @return true or false if the information was saved
     */
    CompletableFuture<Boolean> invoke(OrderFiscalIdentifierDTO orderFiscalIdentifierDTO, Long transactionId);
}
