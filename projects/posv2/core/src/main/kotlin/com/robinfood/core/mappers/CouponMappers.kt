package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.CouponDTO
import com.robinfood.core.entities.transactionrequest.CouponEntity

fun CouponDTO.toCouponEntity(): CouponEntity {
    return CouponEntity(
            code = code,
            value = value
    )
}