package com.robinfood.core.mappers.menu

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity
import java.math.BigDecimal

fun MenuHallProductResponseEntity.toMenuProductResponseDTO(): MenuHallProductResponseDTO? {
    return if (id == null) {
        return null
    } else {
        MenuHallProductResponseDTO(
                articleId ?: DEFAULT_LONG_VALUE,
                brandId ?: DEFAULT_LONG_VALUE,
                description.orEmpty(),
                discount ?: BigDecimal.ZERO,
                displayType ?: DEFAULT_LONG_VALUE,
                groups?.mapNotNull { menuProductGroupDTO -> menuProductGroupDTO.toMenuProductGroupDTO() }
                        ?: emptyList(),
                id,
                image.orEmpty(),
                name.orEmpty(),
                parentId ?: DEFAULT_LONG_VALUE,
                position ?: DEFAULT_INTEGER_VALUE,
                price ?: BigDecimal.ZERO,
                productCategoryId ?: DEFAULT_LONG_VALUE,
                productFlow.orEmpty(),
                sizeId ?: DEFAULT_LONG_VALUE,
                sku.orEmpty(),
                tags ?: emptyList(),
                type ?: DEFAULT_LONG_VALUE,
                typeName.orEmpty()
        )
    }
}
