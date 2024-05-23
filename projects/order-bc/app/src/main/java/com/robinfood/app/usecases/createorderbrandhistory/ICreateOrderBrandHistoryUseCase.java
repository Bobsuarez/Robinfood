package com.robinfood.app.usecases.createorderbrandhistory;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order history brand
 */
public interface ICreateOrderBrandHistoryUseCase {

    /**
     * Create the order history of states by brand
     *
     * @param finalProductDTO List<FinalProductDTO>
     * @param orderId Long
     * @param orderValueTotal
     * @param paid Boolean
     * @param totalPaymentMethods
     * @param userId Long
     *
     * @return CompletableFuture<Boolean>
     */
    CompletableFuture<Boolean> invoke(
            List<FinalProductDTO> finalProductDTO,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            RequestOrderTransactionDTO transactionDTO,
            Double totalPaymentMethods,
            Long userId
    );
}
