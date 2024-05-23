package com.robinfood.app.usecases.createdorderdeduction;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICreateOrderDeductionsUseCase {

    /**
     * Saved the deductions of the transaction
     *
     * @param requestOrderTransactionDTO request transaction
     * @param transactionId  id of the transaction
     * @param orderIds List of id of the orders created
     *
     * @return CompletableFuture<Boolean>
     */
    CompletableFuture<Boolean> invoke(
            RequestOrderTransactionDTO requestOrderTransactionDTO,
            Long transactionId, List<Long> orderIds);
}
