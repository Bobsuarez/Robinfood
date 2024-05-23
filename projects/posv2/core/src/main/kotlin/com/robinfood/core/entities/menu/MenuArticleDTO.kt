package com.robinfood.core.entities.menu

data class MenuArticleDTO (
    val id: Long?,
    val menuHallProductId: Long,
    var typeId: Long,
    var typeName: String
)