package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderCouponDTO
import com.robinfood.core.entities.OrderCouponEntity
import java.math.BigDecimal

fun OrderCouponEntity.toOrderCouponDTO(): OrderCouponDTO {
    return OrderCouponDTO(
            code = code.orEmpty(),
            value = value ?: BigDecimal.ZERO
    )
}

fun OrderCouponDTO.toOrderCouponEntity(): OrderCouponEntity {
    return OrderCouponEntity(
            code = code,
            value = value
    )
}