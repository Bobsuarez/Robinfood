package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.OriginDTO
import com.robinfood.core.entities.transactionrequest.OriginEntity

fun OriginDTO.toOriginEntity(): OriginEntity {
    return OriginEntity(
        id = id,
        name = name
    )
}

fun OriginEntity.toOriginDTO(): OriginDTO {
    return OriginDTO(
            id = id,
            name = name
    )
}