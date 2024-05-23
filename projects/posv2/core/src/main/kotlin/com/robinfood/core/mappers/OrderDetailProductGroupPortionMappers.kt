package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO
import com.robinfood.core.entities.OrderDetailProductGroupPortionEntity
import java.math.BigDecimal

fun OrderDetailProductGroupPortionEntity.toOrderDetailProductGroupPortionDTO(): OrderDetailProductGroupPortionDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailProductGroupPortionDTO(
            changedPortion = changedPortion?.toOrderDetailChangedPortionDTO(),
            discount = discount ?: BigDecimal.ZERO,
            free = free ?: DEFAULT_INTEGER_VALUE,
            id = id,
            isIncluded = !(addition ?: DEFAULT_BOOLEAN_VALUE),
            name = name.orEmpty(),
            parentId = parentId ?: DEFAULT_LONG_VALUE,
            price = price ?: BigDecimal.ZERO,
            quantity = quantity ?: DEFAULT_INTEGER_VALUE,
            sku = sku.orEmpty(),
            unitId = units ?: DEFAULT_LONG_VALUE,
            weight = weight ?: DEFAULT_DOUBLE_VALUE
        )
    }
}