package com.robinfood.core.entities.coupons

import java.math.BigDecimal

data class CouponValidationResponseEntity(
        val codeId: Long?,
        val discount: BigDecimal?,
        val total: BigDecimal?,
        val couponType: Long?
)