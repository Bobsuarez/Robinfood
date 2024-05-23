package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderCouponEntity (
        val code: String?,
        val value: BigDecimal?
)