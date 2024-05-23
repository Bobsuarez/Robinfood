package com.robinfood.core.dtos.transactionresponse

import com.robinfood.core.dtos.OrderCreatedDTO

data class TransactionResponseDTO(
    val id: Long,
    val orders: List<OrderCreatedDTO>,
    val uuid: String
)