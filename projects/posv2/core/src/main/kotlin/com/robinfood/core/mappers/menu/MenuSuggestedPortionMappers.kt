package com.robinfood.core.mappers.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionDataDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionDTO
import com.robinfood.core.entities.menu.MenuSuggestedPortionDataEntity

fun MenuSuggestedPortionDataEntity.toMenuSuggestedPortionDataDTO(): MenuSuggestedPortionDataDTO? {
    return if (id == null || parentId == null) {
        null
    } else {
        MenuSuggestedPortionDataDTO(
                id,
                image.orEmpty(),
                name.orEmpty(),
                parentId,
                sku.orEmpty(),
                dicUnitId?: DEFAULT_LONG_VALUE,
                quantity ?: DEFAULT_DOUBLE_VALUE
        )
    }
}

fun MenuSuggestedPortionDataDTO.toMenuSuggestedPortionDTO(): MenuSuggestedPortionDTO {
    return MenuSuggestedPortionDTO(
            id,
            image,
            name,
            parentId,
            sku,
            unitId,
            unitNumber
    )
}




