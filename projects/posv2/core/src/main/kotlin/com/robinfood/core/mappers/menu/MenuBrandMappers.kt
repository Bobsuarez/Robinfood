package com.robinfood.core.mappers.menu

import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.entities.menu.MenuBrandResponseEntity

fun MenuBrandResponseEntity.toMenuBrandResponseDTO(): MenuBrandResponseDTO? {
    return if (id == null) {
        null
    } else {
        return MenuBrandResponseDTO(
                color = color.orEmpty(),
                id = id,
                image = image.orEmpty(),
                name = name.orEmpty()
        )
    }
}
