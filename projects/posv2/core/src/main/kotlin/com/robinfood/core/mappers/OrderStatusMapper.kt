package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.entities.OrderStatusEntity

fun OrderStatusEntity.toOrderStatusDTO(): OrderStatusDTO {
    return OrderStatusDTO(
            id = id,
            name = name
    )
}