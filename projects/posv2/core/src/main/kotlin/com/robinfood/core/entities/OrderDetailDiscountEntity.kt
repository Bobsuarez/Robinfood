package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailDiscountEntity (
        val id: Long?,
        val orderId: Long?,
        val productId: Long?,
        val typeId: Long?,
        val value: BigDecimal?
)