package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.entities.transactionrequest.BrandEntity

fun BrandDTO.toBrandEntity(): BrandEntity {
    return BrandEntity(
        id = id,
        name = name
    )
}

fun BrandEntity.toBrandDTO(): BrandDTO {
    return BrandDTO(
            id = id,
            name = name
    )
}