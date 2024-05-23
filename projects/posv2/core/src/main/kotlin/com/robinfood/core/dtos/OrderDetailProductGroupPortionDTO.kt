package com.robinfood.core.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class OrderDetailProductGroupPortionDTO (
        val changedPortion: OrderDetailChangedPortionDTO?,
        val discount: BigDecimal?,
        val free: Int,
        val id: Long,
        @get:JsonProperty("included") val isIncluded: Boolean,
        val name: String,
        val parentId: Long,
        val price: BigDecimal,
        val quantity: Int,
        var sku: String,
        val unitId: Long,
        val weight: Double
)