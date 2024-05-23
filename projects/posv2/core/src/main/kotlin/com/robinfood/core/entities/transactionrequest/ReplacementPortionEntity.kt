package com.robinfood.core.entities.transactionrequest

data class ReplacementPortionEntity(
    val id: Long,
    val name: String,
    val product: PortionProductEntity,
    val sku: String,
    val unitId: Long,
    val unitNumber: Double
)
