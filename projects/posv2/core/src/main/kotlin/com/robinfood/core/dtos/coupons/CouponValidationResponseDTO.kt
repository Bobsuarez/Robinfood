package com.robinfood.core.dtos.coupons

import java.math.BigDecimal

data class CouponValidationResponseDTO (
        val codeId: Long,
        val discount: BigDecimal,
        val total: BigDecimal,
        val couponType: Long
)