@file:JvmName("OrderHistoryMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderHistoryDTO
import com.robinfood.core.entities.OrderHistoryEntity

fun OrderHistoryDTO.toOrderHistoryEntity(): OrderHistoryEntity {
    return OrderHistoryEntity(
        null,
        null,
        null,
        observation,
        orderId,
        orderStatusId,
        userId
    )
}
