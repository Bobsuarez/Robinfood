@file:JvmName("TransactionMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO
import com.robinfood.core.entities.TransactionEntity

fun TransactionEntity.toTransactionDTO(): ResponseTransactionDTO {
    return ResponseTransactionDTO(
        createdAt,
        null,
        id,
        paid,
        uniqueIdentifier,
        userId,
        value
    )
}

fun RequestTransactionDTO.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        null,
        id,
        paid,
        uniqueIdentifier,
        null,
        userId,
        value
    )
}
