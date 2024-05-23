package com.robinfood.core.entities.coupons

data class CouponValidationRequestEntity (
        val code: String?,
        val order: CouponValidationOrderRequestEntity?,
        val origin: CouponValidationOriginRequestEntity?,
        val payment: CouponValidationPaymentRequestEntity?,
        val user: CouponValidationUserRequestEntity?
)