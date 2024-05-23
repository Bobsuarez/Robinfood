package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionbyuuid;

import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;

import java.util.concurrent.CompletableFuture;

public interface IGetTransactionByUuidUseCase {

    /**
     * Method that searches for a transaction by its id
     *
     * @param transactionUuid transaction id
     *
     * @return {@link TransactionFlowDTO}
     */
    CompletableFuture<Integer> invoke(String transactionUuid);

}
