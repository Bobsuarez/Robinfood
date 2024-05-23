package com.robinfood.core.entities

data class TransactionResponseEntity (
    val id: Long?,
    val orders: List<OrderCreatedEntity>?,
    val uuid: String?
)