package com.robinfood.core.mappers.posrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO
import com.robinfood.core.entities.posrelatedtoastore.StorePosEntity

fun StorePosEntity.toStorePosDTO(): StorePosDTO {
    return StorePosDTO(
        id = id,
        name = name,
        resolutions = resolutions.map { resolutionDTO ->
            resolutionDTO.toResolutionDTO()
        }
    )
}
