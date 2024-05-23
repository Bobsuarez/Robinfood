package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDetailDiscountDTO (
        val id: Long,
        val typeId: Long,
        val value: BigDecimal
)