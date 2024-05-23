@file:JvmName("OrderFlagTogoMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO
import com.robinfood.core.entities.OrderFlagTogoEntity

fun OrderFlagTogoDTO.toOrderFlagTogoEntity(): OrderFlagTogoEntity {
    return OrderFlagTogoEntity(
            null,
            id,
            orderId,
            statusId
    )
}
