package com.robinfood.core.dtos.menu

import com.robinfood.core.entities.menu.MenuArticleDTO
import java.math.BigDecimal

data class MenuSizeDTO (
        val article: MenuArticleDTO,
        val discount: BigDecimal,
        val id: Long,
        val name: String,
        val price: BigDecimal
)
