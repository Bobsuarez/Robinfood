package com.robinfood.core.mappers.menu

import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.entities.menu.BrandMenuResponseEntity

fun BrandMenuResponseEntity.toMenuBrandDTO(menuBrand: MenuBrandResponseDTO): MenuBrandDTO? {
    return if (halls == null) {
        null
    } else {
        val hallsSorted = halls.sortedBy { it.position }
        return MenuBrandDTO(
                backgroundColor = menuBrand.color,
                categories = hallsSorted.mapNotNull { menuHallResponseEntity -> menuHallResponseEntity.toMenuCategoryDTO() },
                id = menuBrand.id,
                imageUrl = menuBrand.image,
                name = menuBrand.name
        )
    }
}