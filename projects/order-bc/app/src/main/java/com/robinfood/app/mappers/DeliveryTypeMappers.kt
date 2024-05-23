@file: JvmName("DeliveryTypeMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.DeliveryTypeDTO
import com.robinfood.core.entities.DeliveryTypeEntity

fun DeliveryTypeEntity.toDeliveryTypeDTO(): DeliveryTypeDTO {
    return DeliveryTypeDTO(
            description,
            id,
            isIntegration,
            isInternalDelivery,
            isOnPremise,
            name
    )
}