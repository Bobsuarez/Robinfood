package com.robinfood.core.mappers.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.MenuBaseProductDTO
import com.robinfood.core.dtos.menu.MenuCategoryDTO
import com.robinfood.core.dtos.menu.MenuSizeDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.entities.menu.MenuHallResponseEntity
import com.robinfood.core.entities.menu.MenuProductResponseEntity
import com.robinfood.core.utils.getSizeName
import java.math.BigDecimal
import java.util.Collections

fun MenuHallResponseEntity.toMenuCategoryDTO(): MenuCategoryDTO? {
    return if (id == null) {
        null
    } else {
        return MenuCategoryDTO(
                baseProducts = products.toBaseProductDTO(),
                id = id,
                name = name.orEmpty(),
                position = position ?: DEFAULT_INTEGER_VALUE
        )
    }
}

fun List<MenuProductResponseEntity>?.toBaseProductDTO(): List<MenuBaseProductDTO> {
    return this?.map {
        menuProductResponseEntity: MenuProductResponseEntity ->  MenuBaseProductDTO(
            brandId = menuProductResponseEntity.brandId ?: DEFAULT_LONG_VALUE,
            description = menuProductResponseEntity.description.orEmpty(),
            displayType = menuProductResponseEntity.displayType ?: DEFAULT_INTEGER_VALUE,
            flowType = menuProductResponseEntity.flow.orEmpty(),
            id = menuProductResponseEntity.parentId ?: DEFAULT_LONG_VALUE,
            image = menuProductResponseEntity.image.orEmpty(),
            name = menuProductResponseEntity.name.orEmpty(),
            sizes = Collections.singletonList(
                    MenuSizeDTO(
                            article = MenuArticleDTO(
                                    DEFAULT_LONG_VALUE,
                                    menuProductResponseEntity.id ?: DEFAULT_LONG_VALUE,
                                    menuProductResponseEntity.typeId ?: DEFAULT_LONG_VALUE,
                                    menuProductResponseEntity.typeName.orEmpty()
                            ),
                            discount = menuProductResponseEntity.discount ?: BigDecimal.ZERO,
                            id = menuProductResponseEntity.sizeId ?: DEFAULT_LONG_VALUE,
                            name = getSizeName(menuProductResponseEntity.sizeId),
                            price = menuProductResponseEntity.price ?: BigDecimal.ZERO
                    )
            )
    )
    } ?: emptyList()
}
