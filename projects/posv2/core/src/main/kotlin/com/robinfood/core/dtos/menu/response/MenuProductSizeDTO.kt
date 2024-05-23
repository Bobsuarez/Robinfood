package com.robinfood.core.dtos.menu.response

import com.robinfood.core.entities.menu.MenuArticleDTO
import java.math.BigDecimal

data class MenuProductSizeDTO(
        val article: MenuArticleDTO,
        val discount: BigDecimal,
        val groups: List<MenuSizeGroupDTO>,
        val id: Long,
        val name: String,
        val price: BigDecimal
)