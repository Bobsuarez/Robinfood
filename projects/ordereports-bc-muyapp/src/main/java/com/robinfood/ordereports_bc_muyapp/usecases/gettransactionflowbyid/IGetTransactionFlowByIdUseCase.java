package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionflowbyid;

import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;

import java.util.concurrent.CompletableFuture;

public interface IGetTransactionFlowByIdUseCase {

    /**
     * Method that searches for a transaction by its id
     *
     * @param id transaction id
     *
     * @return {@link TransactionFlowDTO}
     */
    CompletableFuture<Short> invoke(Long id);

}
