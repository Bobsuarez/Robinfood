package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDetailProductDiscountDTO(
        val id: Long,
        val typeId: Long,
        var value: BigDecimal
)