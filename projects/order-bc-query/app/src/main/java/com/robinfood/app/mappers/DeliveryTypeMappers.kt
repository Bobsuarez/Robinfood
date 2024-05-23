@file: JvmName("DeliveryTypeMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.DeliveryTypeDTO
import com.robinfood.core.entities.DeliveryTypeEntity

fun DeliveryTypeEntity.toDeliveryTypeDTO(): DeliveryTypeDTO {

    return DeliveryTypeDTO.builder()
        .description(description)
        .id(id)
        .isIntegration(isIntegration)
        .isInternalDelivery(isInternalDelivery)
        .isOnPremise(isOnPremise)
        .name(name)
        .build()
}