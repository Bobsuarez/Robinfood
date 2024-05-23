package com.robinfood.core.mappers.store

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.entities.store.StoreDeliveryPlatformEntity
import com.robinfood.core.entities.store.StoreDeliveryPlatformResponseEntity
import java.util.Collections

fun StoreDeliveryPlatformResponseEntity.toDeliveryPlatformListDTO(): List<StoreDeliveryPlatformDTO> {
    return content?.mapNotNull { deliveryPlatformResponseEntity ->
        deliveryPlatformResponseEntity.toDeliveryPlatformDTO()
    } ?: Collections.emptyList()
}

fun StoreDeliveryPlatformEntity.toDeliveryPlatformDTO(): StoreDeliveryPlatformDTO? {
    return if (id == null) {
        return null
    } else StoreDeliveryPlatformDTO(
            color = color.orEmpty(),
            flowId = flowId ?: DEFAULT_LONG_VALUE,
            id = id,
            imageUrl= image.orEmpty(),
            name = name.orEmpty(),
            slug = slug.orEmpty(),
            status = status ?: DEFAULT_LONG_VALUE
    )
}