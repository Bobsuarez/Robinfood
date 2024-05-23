package com.robinfood.core.dtos.menu.response

import com.robinfood.core.dtos.MenuBrandDTO

data class MenuProductDetailCrossSellingDTO(
        val brand: MenuBrandDTO,
        val category: CategoryDTO,
        val description: String,
        val flowType: String,
        val id: Long,
        val image: String,
        val name: String,
        val sizes: List<MenuProductSizeDTO>,
        val sku: String
)