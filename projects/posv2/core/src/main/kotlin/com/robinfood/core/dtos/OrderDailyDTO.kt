package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDailyDTO(
    val id: Long,
    val orderNumber: String,
    val invoiceNumber: String,
    val totalPrice: BigDecimal,
    val deliveryTypeId: Int,
    val originId: Long,
    val origin: String,
    val originBackgroundColor: String,
    val brandId: Long,
    val brandImage: String,
    val brandBackgroundColor: String,
    val userId: Long,
    val customerName: String,
    val creationTime: String
)
