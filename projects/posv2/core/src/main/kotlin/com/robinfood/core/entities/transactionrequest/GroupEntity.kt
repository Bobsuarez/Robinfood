package com.robinfood.core.entities.transactionrequest

data class GroupEntity(
    val id: Long,
    val name: String,
    val portions: List<PortionEntity>,
    val sku: String
)
