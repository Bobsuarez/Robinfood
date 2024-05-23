package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderDetailProductGroupDTO
import com.robinfood.core.entities.OrderDetailProductGroupEntity

fun OrderDetailProductGroupEntity.toOrderDetailProductGroupDTO(): OrderDetailProductGroupDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailProductGroupDTO(
                id = id,
                name = name.orEmpty(),
                portions = portions?.mapNotNull { orderDetailProductGroupPortionEntity ->
                    orderDetailProductGroupPortionEntity.toOrderDetailProductGroupPortionDTO()
                } ?: emptyList(),
                removedPortions = removedPortions?.mapNotNull { orderDetailGroupRemovedPortionEntity ->
                    orderDetailGroupRemovedPortionEntity.toOrderDetailGroupRemovedPortionDTO()
                } ?: emptyList(),
                sku = sku.orEmpty()
        )
    }
}