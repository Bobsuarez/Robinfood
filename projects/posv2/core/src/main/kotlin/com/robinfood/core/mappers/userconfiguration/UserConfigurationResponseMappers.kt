package com.robinfood.core.mappers.userconfiguration

import com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.userconfiguration.PosResponseDTO
import com.robinfood.core.dtos.userconfiguration.StoreResponseDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.entities.userconfiguration.PosEntity
import com.robinfood.core.entities.userconfiguration.StoreEntity
import com.robinfood.core.entities.userconfiguration.UserConfigurationEntity

fun PosEntity.toPosResponseDTO(): PosResponseDTO? {
    return if (id == null) {
        null
    } else PosResponseDTO(
            id = id,
            name = name.orEmpty(),
            currency = currency.orEmpty(),
            countryId = countryId ?: DEFAULT_LONG_VALUE,
            isDelivery = isDelivery ?: DEFAULT_BOOLEAN_VALUE,
            flowId = flowId ?: DEFAULT_LONG_VALUE,
            isMultiBrand = isMultiBrand ?: DEFAULT_BOOLEAN_VALUE
    )
}

fun StoreEntity.toStoreResponseDTO(): StoreResponseDTO? {
    return if (id == null) {
        null
    } else StoreResponseDTO(
            address = address.orEmpty(),
            city = city.orEmpty(),
            country = country.orEmpty(),
            id = id,
            internalName = internalName.orEmpty(),
            name = name.orEmpty(),
            timeZone = timeZone.orEmpty(),
            uuid = uuid.orEmpty()
    )
}

fun UserConfigurationEntity.toUserConfigurationResponseDTO(): UserConfigurationResponseDTO? {
    return if (store == null || pos == null) {
        null
    } else {
        val posResponse : PosResponseDTO? = pos.toPosResponseDTO()
        val storeResponse : StoreResponseDTO? = store.toStoreResponseDTO()
        if (storeResponse == null || posResponse == null) {
            null
        } else {
            UserConfigurationResponseDTO(
                    pos = posResponse,
                    store = storeResponse
            )
        }
    }
}
