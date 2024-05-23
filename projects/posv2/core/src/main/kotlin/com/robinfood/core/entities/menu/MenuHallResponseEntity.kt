package com.robinfood.core.entities.menu

data class MenuHallResponseEntity(
        val id: Long?,
        val name: String?,
        val position: Int?,
        val products: List<MenuProductResponseEntity>?,
        val status: Long?
)
