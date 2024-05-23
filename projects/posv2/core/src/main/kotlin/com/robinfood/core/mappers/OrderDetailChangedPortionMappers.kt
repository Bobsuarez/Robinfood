package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO
import com.robinfood.core.entities.OrderDetailChangedPortionEntity

fun OrderDetailChangedPortionEntity.toOrderDetailChangedPortionDTO(): OrderDetailChangedPortionDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailChangedPortionDTO(
                id = id,
                name = name.orEmpty(),
                parentId = parentId ?: DEFAULT_LONG_VALUE,
                sku = sku.orEmpty(),
                unitId = unitId ?: DEFAULT_LONG_VALUE,
                unitNumber = unitNumber ?: DEFAULT_DOUBLE_VALUE
        )
    }
}