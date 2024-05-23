package com.robinfood.app.usecases.createorderhistory;

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
     * @param totalPaymentMethods
     * @param userId Long
     *
     * @return CompletableFuture<Boolean>
     */
    CompletableFuture<Boolean> invoke(
            String note,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            Double totalPaymentMethods,
            Long userId
    );
}
