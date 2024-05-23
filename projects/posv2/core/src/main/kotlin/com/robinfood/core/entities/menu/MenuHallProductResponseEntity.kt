package com.robinfood.core.entities.menu

import java.math.BigDecimal

data class MenuHallProductResponseEntity(
        val articleId: Long?,
        val brandId: Long?,
        val description: String?,
        val discount: BigDecimal?,
        val displayType: Long?,
        val groups: List<MenuProductGroupEntity>?,
        val id: Long?,
        val image: String?,
        val name: String?,
        val parentId: Long?,
        val position: Int?,
        val price: BigDecimal?,
        val productCategoryId: Long?,
        val productFlow: String?,
        val sizeId: Long?,
        val sku: String?,
        val tags: List<String>?,
        val type: Long?,
        val typeName: String?
)
