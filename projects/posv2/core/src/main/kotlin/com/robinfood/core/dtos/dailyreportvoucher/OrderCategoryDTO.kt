package com.robinfood.core.dtos.dailyreportvoucher

data class OrderCategoryDTO(
    val id: Long?,
    val name: String,
    val compensation: Double,
    val grossValue: Double,
    val discounts: Double,
    val taxes: Double,
    val netValue: Double
)