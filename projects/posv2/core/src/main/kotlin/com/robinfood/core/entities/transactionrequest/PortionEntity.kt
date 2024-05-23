package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class PortionEntity(
    val discount: BigDecimal,
    val free: Int,
    val id: Long,
    val isIncluded: Boolean,
    val name: String,
    val price: BigDecimal,
    val product: PortionProductEntity,
    val quantity: Int,
    val replacementPortion: ReplacementPortionEntity?,
    val sku: String,
    val unitId: Long,
    val unitNumber: Double
)
