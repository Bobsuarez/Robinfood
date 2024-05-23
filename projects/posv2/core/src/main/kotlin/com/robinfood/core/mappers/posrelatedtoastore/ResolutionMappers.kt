package com.robinfood.core.mappers.posrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.ResolutionDTO
import com.robinfood.core.entities.posrelatedtoastore.ResolutionEntity

fun ResolutionEntity.toResolutionDTO(): ResolutionDTO {
    return ResolutionDTO(
        finalNumber = finalNumber,
        id = id,
        prefix = prefix,
        startingNumber = startingNumber
    )
}
