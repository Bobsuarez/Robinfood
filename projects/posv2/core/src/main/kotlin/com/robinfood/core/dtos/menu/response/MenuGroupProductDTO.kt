package com.robinfood.core.dtos.menu.response

import java.math.BigDecimal

data class MenuGroupProductDTO(
    val appliesSubsidyIfRemoved: Boolean,
    val discount: BigDecimal,
    val id: Long,
    val imageUrl: String,
    val isIncluded: Boolean,
    val name: String,
    val parentId: Long,
    val price: BigDecimal,
    val selectionType: Int,
    val sku: String,
    var suggestedPortions: List<MenuSuggestedPortionDTO>,
    val unitId: Long,
    val weight: Double
)