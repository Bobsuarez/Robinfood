package com.robinfood.core.dtos.dailyreportvoucher

data class CategoryReportDTO (
    val id: Long,
    val name: String,
    val co2Compensation: Double,
    val subtotal: Double,
    val discounts: Double,
    val taxes: Double,
    val total: Double,
    var taxPercentage: Double
)