package com.robinfood.core.entities.dailyreportvoucher

data class OrderCategoryEntity(
    val id: Long?,
    val name: String,
    val compensation: Double,
    val grossValue: Double,
    val discounts: Double,
    val taxes: Double,
    val netValue: Double
)