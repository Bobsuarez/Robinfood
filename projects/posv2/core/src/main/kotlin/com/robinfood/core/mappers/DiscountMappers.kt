package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.DiscountDTO
import com.robinfood.core.entities.transactionrequest.DiscountEntity

fun DiscountDTO.toDiscountEntity(): DiscountEntity {
    return DiscountEntity(
        id = id,
        value = value
    )
}