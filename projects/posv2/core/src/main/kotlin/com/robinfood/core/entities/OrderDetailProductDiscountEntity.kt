package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailProductDiscountEntity(
        val id: Long?,
        val typeId: Long?,
        val value: BigDecimal?
)
