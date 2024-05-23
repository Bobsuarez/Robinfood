package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class CouponEntity(
        val code: String?,
        val value: BigDecimal?
)