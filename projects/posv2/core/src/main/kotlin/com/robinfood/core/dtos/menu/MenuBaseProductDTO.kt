package com.robinfood.core.dtos.menu

data class MenuBaseProductDTO (
        val brandId: Long,
        val description: String,
        val displayType: Int,
        val flowType: String,
        val id: Long,
        val image: String,
        val name: String,
        val sizes: List<MenuSizeDTO>
)
