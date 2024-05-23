package com.robinfood.core.dtos.menu

data class MenuCategoryDTO (
        val baseProducts: List<MenuBaseProductDTO>,
        val id: Long,
        val name: String,
        val position: Int
)