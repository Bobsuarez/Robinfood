package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderCreatedDTO (
    val discountPrice: BigDecimal,
    val id: Long,
    val orderInvoiceNumber: String,
    val orderNumber: String,
    val subtotal: BigDecimal,
    val taxPrice: BigDecimal,
    val total: BigDecimal,
    val uid: String
)