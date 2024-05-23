@file:JvmName("OrderFlagIntegrationMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO
import com.robinfood.core.entities.OrderFlagIntegrationEntity

fun OrderFlagIntegrationDTO.toOrderFlagIntegrationEntity(): OrderFlagIntegrationEntity {
    return OrderFlagIntegrationEntity(
            null,
            id,
            orderId
    )
}