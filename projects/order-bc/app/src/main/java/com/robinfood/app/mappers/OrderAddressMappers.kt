package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderAddressDTO
import com.robinfood.core.entities.OrderAddressEntity

fun OrderAddressEntity.toOrderAddressDTO(): OrderAddressDTO {

    return OrderAddressDTO(
            address,
            cityId,
            countryId,
            latitude,
            longitude,
            notes,
            orderId,
            transactionId,
            zipCode
    )
}

fun OrderAddressDTO.toOrderAddressEntity(): OrderAddressEntity {

    return OrderAddressEntity(
        address,
        cityId,
        countryId,
        latitude,
        longitude,
        notes,
        orderId,
        transactionId,
        zipCode,
        null,
        null
    )
}