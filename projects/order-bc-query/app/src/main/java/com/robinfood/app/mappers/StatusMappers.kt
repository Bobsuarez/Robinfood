@file:JvmName("StatusMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.entities.StatusEntity

fun StatusEntity.toStatusDTO(): OrderStatusDTO {
    return OrderStatusDTO(
            id,
            name
    )
}