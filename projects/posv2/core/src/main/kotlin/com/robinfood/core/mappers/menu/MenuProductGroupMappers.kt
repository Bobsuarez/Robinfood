package com.robinfood.core.mappers.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.response.MenuProductGroupDTO
import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO
import com.robinfood.core.entities.menu.MenuProductGroupEntity
import com.robinfood.core.extensions.toGroupSelectionType

fun MenuProductGroupEntity.toMenuProductGroupDTO(): MenuProductGroupDTO? {
    return if (id == null) {
        return null
    } else MenuProductGroupDTO(
        free ?: DEFAULT_INTEGER_VALUE,
        groupTypeId ?: DEFAULT_LONG_VALUE,
        id,
        max ?: DEFAULT_INTEGER_VALUE,
        min ?: DEFAULT_INTEGER_VALUE,
        namePlural.orEmpty(),
        nameSingular.orEmpty(),
        portions?.mapNotNull { menuGroupPortionEntity -> menuGroupPortionEntity.toMenuGroupPortionDTO() } ?: emptyList(),
        selectionNamePlural.orEmpty(),
        selectionNameSingular.orEmpty(),
        sku.orEmpty(),
        subsidy ?: DEFAULT_INTEGER_VALUE
    )
}

fun MenuProductGroupDTO.toMenuSizeGroupDTO(): MenuSizeGroupDTO {
    return MenuSizeGroupDTO(
        id,
        nameSingular,
        namePlural,
        nameSingular,
        portions.map { portion -> portion.toMenuGroupProductDTO(toGroupSelectionType()) },
        free,
        max,
        min,
        selectionNamePlural,
        selectionNameSingular,
        sku,
        subsidy
    )
}
