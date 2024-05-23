package com.robinfood.core.dtos.menu.response

import java.math.BigDecimal

data class MenuHallProductResponseDTO(
        val articleId: Long,
        val brandId: Long,
        val description: String,
        val discount: BigDecimal,
        val displayType: Long,
        val groups: List<MenuProductGroupDTO>,
        val id: Long,
        val image: String,
        val name: String,
        val parentId: Long,
        val position: Int,
        val price: BigDecimal,
        val productCategoryId: Long,
        val productFlow: String,
        val sizeId: Long,
        val sku: String,
        val tags: List<String>,
        val type: Long,
        val typeName: String
)
