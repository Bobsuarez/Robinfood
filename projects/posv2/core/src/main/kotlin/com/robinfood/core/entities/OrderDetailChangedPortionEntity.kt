package com.robinfood.core.entities

data class OrderDetailChangedPortionEntity(
        val id: Long?,
        val name: String?,
        val parentId: Long?,
        val sku: String?,
        val unitId: Long?,
        val unitNumber: Double?
)
