package com.robinfood.core.dtos.menu.response

data class MenuSuggestedPortionDTO (
        val id: Long,
        val imageUrl: String,
        val name: String,
        val parentId: Long,
        val sku: String,
        val unitId: Long,
        val unitNumber: Double
)