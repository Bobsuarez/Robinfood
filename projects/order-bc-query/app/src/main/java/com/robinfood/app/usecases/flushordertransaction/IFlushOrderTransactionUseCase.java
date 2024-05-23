package com.robinfood.app.usecases.flushordertransaction;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that create order transaction
 */
public interface IFlushOrderTransactionUseCase {

    /**
     * Flush orders for recreate
     * @param orderTransactionDTO transaction id
     */
    CompletableFuture<Void> invoke(RequestOrderTransactionDTO orderTransactionDTO);
}
