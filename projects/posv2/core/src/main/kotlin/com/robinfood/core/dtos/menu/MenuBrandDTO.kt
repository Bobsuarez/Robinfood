package com.robinfood.core.dtos.menu

data class MenuBrandDTO (
        val backgroundColor: String,
        val categories: List<MenuCategoryDTO>,
        val id: Long,
        val imageUrl: String,
        val name: String
)
