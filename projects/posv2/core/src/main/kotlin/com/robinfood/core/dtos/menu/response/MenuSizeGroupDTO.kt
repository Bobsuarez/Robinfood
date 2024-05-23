package com.robinfood.core.dtos.menu.response

data class MenuSizeGroupDTO(
        val id: Long,
        val name: String,
        val namePlural: String,
        val nameSingular: String,
        var portions: List<MenuGroupProductDTO>,
        val selectFree: Int,
        val selectMax: Int,
        val selectMin: Int,
        val selectionNamePlural: String,
        val selectionNameSingular: String,
        val sku: String,
        val subsidy: Int
)