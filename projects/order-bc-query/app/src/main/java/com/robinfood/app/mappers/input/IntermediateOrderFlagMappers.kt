@file:JvmName("InputIntermediateOrderFlagMappers")
package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.IntermediateOrderFlagDTO
import com.robinfood.core.entities.OrderFlagEntity

fun IntermediateOrderFlagDTO.toOrderFlagEntity(): OrderFlagEntity {
    return OrderFlagEntity(
        flag,
        id,
        orderId
    )
}
