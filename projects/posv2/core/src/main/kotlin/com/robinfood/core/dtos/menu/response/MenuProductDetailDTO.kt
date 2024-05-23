package com.robinfood.core.dtos.menu.response

import com.robinfood.core.dtos.MenuBrandDTO

data class MenuProductDetailDTO(
        val brand: MenuBrandDTO,
        val description: String,
        var displayType: Long,
        val flowType: String,
        val id: Long,
        val image: String,
        val name: String,
        val productCategoryId: Long,
        val sizes: List<MenuProductSizeDTO>,
        val sku: String
)