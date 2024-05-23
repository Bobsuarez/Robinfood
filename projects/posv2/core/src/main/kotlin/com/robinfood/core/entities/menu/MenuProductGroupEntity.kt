package com.robinfood.core.entities.menu

data class MenuProductGroupEntity(
    val free: Int?,
    val groupTypeId: Long?,
    val id: Long?,
    val max: Int?,
    val min: Int?,
    val namePlural: String?,
    val nameSingular: String?,
    val portions: List<MenuGroupPortionEntity>?,
    val selectionNamePlural: String?,
    val selectionNameSingular: String?,
    val sku: String?,
    val subsidy: Int?
)