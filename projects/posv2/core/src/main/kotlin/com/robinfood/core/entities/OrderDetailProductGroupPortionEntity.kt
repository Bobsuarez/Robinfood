package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailProductGroupPortionEntity(
        val addition: Boolean?,
        val changedPortion: OrderDetailChangedPortionEntity?,
        val discount: BigDecimal?,
        val free: Int?,
        val id: Long?,
        val name: String?,
        val parentId: Long?,
        val price: BigDecimal?,
        val quantity: Int?,
        val sku: String?,
        val units: Long?,
        val weight: Double?
)
