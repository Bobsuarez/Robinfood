package com.robinfood.core.entities.orderdaily

import java.math.BigDecimal

data class OrderDailyEntity(
    val brand: OrderBrandDailyEntity,
    val brandId: Long,
    val createdAt: String,
    val deliveryTypeId: Int,
    val id: Long,
    val orderInvoiceNumber: String,
    val orderNumber: String,
    val origin: OrderOriginDailyEntity,
    val total: BigDecimal,
    val user: OrderDailyUserEntity
)
