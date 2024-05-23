package com.robinfood.app.mappers

import com.robinfood.core.constants.GlobalConstants
import com.robinfood.core.dtos.CouponsDTO
import com.robinfood.core.dtos.OrderCouponDTO
import com.robinfood.core.entities.OrderCouponEntity

fun OrderCouponDTO.toResponseOrderDiscountEntity(): OrderCouponEntity {
    return OrderCouponEntity(
            code,
            couponType,
            null,
            GlobalConstants.DEFAULT_INTEGER_VALUE,
            redeemedId,
            transactionId,
            null,
            value
    )
}

fun OrderCouponEntity.toResponseOrderDiscountDTO(): OrderCouponDTO {
    return OrderCouponDTO(
            code,
            couponType,
            redeemedId,
            transactionId,
            value
    )
}

fun OrderCouponDTO.toResponseCouponsDTO(): CouponsDTO {
    return CouponsDTO(
            code,
            value
    )
}
