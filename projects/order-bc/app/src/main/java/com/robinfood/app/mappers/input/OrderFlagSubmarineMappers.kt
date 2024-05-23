@file:JvmName("InputOrderFlagSubmarineMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO
import com.robinfood.core.entities.OrderFlagSubmarineEntity

fun OrderFlagSubmarineDTO.toOrderFlagSubmarineEntity(): OrderFlagSubmarineEntity {
    return OrderFlagSubmarineEntity(
            null,
            id,
            orderId
    )
}
