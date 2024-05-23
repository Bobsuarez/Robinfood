package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CouponDTO(

        @NotBlank(message = "Coupon code must not be empty")
        val code: String,

        @Min(0)
        @NotNull
        val value: BigDecimal
)