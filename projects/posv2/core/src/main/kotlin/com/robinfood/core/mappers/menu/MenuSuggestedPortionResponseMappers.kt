package com.robinfood.core.mappers.menu

import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity

fun MenuSuggestedPortionResponseEntity.toMenuSuggestedPortionResponseDTO(): MenuSuggestedPortionResponseDTO? {
    return if (id == null || parentId == null) {
        null
    } else MenuSuggestedPortionResponseDTO(
            id,
            image.orEmpty(),
            name.orEmpty(),
            parentId,
            sku.orEmpty(),
            changes?.mapNotNull { change -> change.toMenuSuggestedPortionDataDTO() } ?: emptyList()
    )
}

