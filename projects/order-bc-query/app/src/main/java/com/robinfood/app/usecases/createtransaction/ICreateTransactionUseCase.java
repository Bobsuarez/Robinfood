package com.robinfood.app.usecases.createtransaction;

import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag togo
 */
public interface ICreateTransactionUseCase {

    /**
     * Retrieves the order flag togo
     * @param transactionDTO for creating an transaction
     * @return the structure of creating an transaction DTO
     */
    CompletableFuture<ResponseTransactionDTO> invoke(RequestTransactionDTO transactionDTO);
}
