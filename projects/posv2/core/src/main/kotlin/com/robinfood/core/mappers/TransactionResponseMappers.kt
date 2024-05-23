package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.dtos.transactionresponse.TransactionResponseDTO
import com.robinfood.core.entities.TransactionCreatedResponseEntity
import com.robinfood.core.entities.TransactionResponseEntity

fun TransactionCreatedResponseEntity.toTransactionCreatedResponseDTO(): TransactionCreatedResponseDTO {
    return TransactionCreatedResponseDTO(
        transaction = transaction.toTransactionResponseDTO()
    )
}

fun TransactionResponseEntity.toTransactionResponseDTO(): TransactionResponseDTO {
    return TransactionResponseDTO(
        id = id ?: DEFAULT_LONG_VALUE,
        orders = orders?.map { orderCreatedEntity -> orderCreatedEntity.toOrderCreatedDTO() } ?: emptyList(),
        uuid = uuid.orEmpty()
    )
}