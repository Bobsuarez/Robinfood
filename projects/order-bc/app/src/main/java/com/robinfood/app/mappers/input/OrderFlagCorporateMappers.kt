@file:JvmName("InputOrderFlagCorporateMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO
import com.robinfood.core.entities.OrderFlagCorporateEntity

fun OrderFlagCorporateDTO.toOrderFlagCorporateEntity(): OrderFlagCorporateEntity {
    return OrderFlagCorporateEntity(
            null,
            id,
            orderId
    )
}
