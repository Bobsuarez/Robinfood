package com.robinfood.core.dtos.menu.response

data class MenuProductGroupDTO(
    val free: Int,
    val groupTypeId: Long,
    val id: Long,
    val max: Int,
    val min: Int,
    val namePlural: String,
    val nameSingular: String,
    val portions: List<MenuGroupPortionDTO>,
    val selectionNamePlural: String,
    val selectionNameSingular: String,
    val sku: String,
    val subsidy: Int
)