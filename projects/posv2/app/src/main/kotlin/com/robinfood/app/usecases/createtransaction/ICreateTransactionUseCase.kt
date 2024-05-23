package com.robinfood.app.usecases.createtransaction

import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that creates a transaction
 */
interface ICreateTransactionUseCase {

    /**
     * Sends a request to create a transaction
     * @param token the authentication token
     * @param transactionRequestDTO the json body request with the transaction info
     * @return the order created
     */
    suspend fun invoke(
        token: String,
        transactionRequestDTO: TransactionRequestDTO
    ): Result<TransactionCreatedResponseDTO>
}