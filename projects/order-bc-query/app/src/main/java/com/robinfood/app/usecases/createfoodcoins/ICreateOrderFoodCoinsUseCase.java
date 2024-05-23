package com.robinfood.app.usecases.createfoodcoins;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICreateOrderFoodCoinsUseCase {
    /**
     * Saved foodcoins of the transaction
     *
     * @param requestOrderTransactionDTO request transaction
     * @param orderIds List of id of the orders created
     *
     * @return CompletableFuture<Boolean>
     */
    CompletableFuture<Boolean> invoke(
            RequestOrderTransactionDTO requestOrderTransactionDTO,
             List<Long> orderIds);
}
