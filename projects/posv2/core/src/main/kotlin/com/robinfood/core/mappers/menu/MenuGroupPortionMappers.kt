package com.robinfood.core.mappers.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.response.MenuGroupPortionDTO
import com.robinfood.core.dtos.menu.response.MenuGroupProductDTO
import com.robinfood.core.entities.menu.MenuGroupPortionEntity
import java.math.BigDecimal

fun MenuGroupPortionEntity.toMenuGroupPortionDTO(): MenuGroupPortionDTO? {
    return if (id == null || parentId == null) {
        null
    } else MenuGroupPortionDTO(
        default ?: DEFAULT_BOOLEAN_VALUE,
        discount ?: BigDecimal.ZERO,
        id,
        image.orEmpty(),
        name.orEmpty(),
        parentId,
        premiumPrice ?: BigDecimal.ZERO,
        price?: BigDecimal.ZERO,
        position ?: DEFAULT_INTEGER_VALUE,
        sku.orEmpty(),
        unit ?: DEFAULT_LONG_VALUE,
        weight ?: DEFAULT_DOUBLE_VALUE
    )
}

fun MenuGroupPortionDTO.toMenuGroupProductDTO(selectionType: Int): MenuGroupProductDTO {
    return MenuGroupProductDTO(
        DEFAULT_BOOLEAN_VALUE,
        discount,
        id,
        image,
        default,
        name,
        parentId,
        price,
        selectionType,
        sku,
        emptyList(),
        unit,
        weight
    )
}