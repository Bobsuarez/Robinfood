package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderDetailDiscountDTO
import com.robinfood.core.entities.OrderDetailDiscountEntity
import java.math.BigDecimal

fun OrderDetailDiscountEntity.toOrderDetailDiscountDTO(): OrderDetailDiscountDTO? {
    return if (id == null || typeId == null) {
        null
    } else {
        return OrderDetailDiscountDTO(
                id = id,
                typeId = typeId,
                value = value ?: BigDecimal.ZERO
        )
    }
}