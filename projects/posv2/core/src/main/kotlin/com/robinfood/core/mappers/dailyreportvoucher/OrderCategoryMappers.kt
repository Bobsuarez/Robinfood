package com.robinfood.core.mappers.dailyreportvoucher

import com.robinfood.core.constants.GlobalConstants.FORMAT_DOUBLE_TWO_DECIMAL
import com.robinfood.core.dtos.dailyreportvoucher.CategoryReportDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.entities.dailyreportvoucher.OrderCategoryEntity

fun OrderCategoryDTO.toCategoryReportDTO(): CategoryReportDTO? {
    return if (id == null) {
        return null
    } else CategoryReportDTO(
        id = id,
        name = name,
        co2Compensation = compensation,
        subtotal = grossValue,
        discounts = discounts,
        taxes = taxes,
        total = netValue,
        taxPercentage = 0.00
    )
}

fun OrderCategoryEntity.toOrderCategoryDTO(): OrderCategoryDTO? {
    return if (id == null) {
        return null
    } else OrderCategoryDTO(
        id = id,
        name = name,
        compensation = FORMAT_DOUBLE_TWO_DECIMAL.format(compensation).toDouble(),
        grossValue = FORMAT_DOUBLE_TWO_DECIMAL.format(grossValue).toDouble(),
        discounts = FORMAT_DOUBLE_TWO_DECIMAL.format(discounts).toDouble(),
        taxes = FORMAT_DOUBLE_TWO_DECIMAL.format(taxes).toDouble(),
        netValue = FORMAT_DOUBLE_TWO_DECIMAL.format(netValue).toDouble()
    )
}