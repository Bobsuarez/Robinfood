package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO
import com.robinfood.core.entities.OrderDetailProductDiscountEntity
import java.math.BigDecimal

fun OrderDetailProductDiscountEntity.toOrderDetailProductDiscountDTO(): OrderDetailProductDiscountDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailProductDiscountDTO(
                id = id,
                typeId = typeId ?: DEFAULT_LONG_VALUE,
                value = value ?: BigDecimal.ZERO
        )
    }
}