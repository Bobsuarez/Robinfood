package com.robinfood.core.dtos.coupons

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class CouponValidationRequestDTO (

        @NotNull
        val code: String,

        @NotNull
        @Valid
        val order: CouponValidationOrderRequestDTO,

        @NotNull
        @Valid
        val origin: CouponValidationOriginRequestDTO,

        @NotNull
        @Valid
        val payment: CouponValidationPaymentRequestDTO,

        @NotNull
        @Valid
        val user: CouponValidationUserRequestDTO
)