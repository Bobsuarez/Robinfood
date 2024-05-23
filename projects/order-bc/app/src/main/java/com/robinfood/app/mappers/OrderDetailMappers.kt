@file:JvmName("OrderDetailMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.entities.OrderDetailEntity

fun OrderDetailEntity.toOrderDetailDTO(): OrderDetailDTO {
    return OrderDetailDTO(
            consumptionValue,
            hasConsumption,
            orderId,
            invoice,
            notes
    )
}
