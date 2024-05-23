package com.robinfood.core.entities.coupons

import java.math.BigDecimal

data class CouponValidationPaymentRequestEntity (
        val paymentMethodId: Long?,
        val total: BigDecimal?
)