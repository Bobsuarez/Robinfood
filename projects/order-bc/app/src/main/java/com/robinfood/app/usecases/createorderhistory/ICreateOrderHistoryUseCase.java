package com.robinfood.app.usecases.createorderhistory;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order history
 */
public interface ICreateOrderHistoryUseCase {

    /**
     * Create the order history of states
     *
     * @param note String
     * @param orderId Long
     * @param paid Boolean
     * @param transaction String
     * @param totalPaymentMethods Long
     * @param userId Long
     *
     * @return CompletableFuture<Boolean>
     */
    CompletableFuture<Boolean> invoke(
            String note,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            RequestOrderTransactionDTO transaction,
            Double totalPaymentMethods,
            Long userId
    );
}
