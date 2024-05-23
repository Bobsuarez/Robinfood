package com.robinfood.core.dtos.coupons

import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class CouponValidationPaymentRequestDTO (

        @NotNull(message = "Payment method id should not be null")
        val paymentMethodId: Long,

        @NotNull(message = "Total should not be null")
        val total: BigDecimal
)