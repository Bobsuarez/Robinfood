package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailGroupRemovedPortionDTO
import com.robinfood.core.entities.OrderDetailGroupRemovedPortionEntity

fun OrderDetailGroupRemovedPortionEntity.toOrderDetailGroupRemovedPortionDTO(): OrderDetailGroupRemovedPortionDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailGroupRemovedPortionDTO(
                id = id,
                name = name.orEmpty(),
                parentId = parentId ?: DEFAULT_LONG_VALUE
        )
    }
}