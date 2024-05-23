package com.robinfood.core.dtos

data class OrderCreatedResponseDTO (
    val orders: List<OrderCreatedDTO>,
    val transactionId: Long
)