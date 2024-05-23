package com.robinfood.core.entities.coupons

data class CouponValidationOrderRequestEntity (
        val orderId: Long?,
        val flowId: Long?,
        val products: List<CouponValidationOrderProductRequestEntity>?
)