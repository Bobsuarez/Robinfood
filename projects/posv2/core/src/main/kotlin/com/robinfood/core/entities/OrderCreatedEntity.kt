package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderCreatedEntity (
    val discountPrice: BigDecimal?,
    val id: Long?,
    val orderInvoiceNumber: String?,
    val orderNumber: String?,
    val subtotal: BigDecimal?,
    val taxPrice: BigDecimal?,
    val total: BigDecimal?,
    val uid: String?
)