package com.robinfood.repository.transactions

import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.entities.transactionrequest.TransactionRequestEntity
import com.robinfood.core.enums.Result

interface ITransactionRepository {

    /**
     * Sends a request to create an order
     * @param token the authentication token
     * @param transactionRequestEntity the json body request with the order request info
     * @return the order created DTO
     */
    suspend fun createTransaction(
        token: String,
        transactionRequestEntity: TransactionRequestEntity
    ): Result<TransactionCreatedResponseDTO>
}