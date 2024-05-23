package com.robinfood.core.dtos

data class OrderDetailChangedPortionDTO(
    val id: Long?,
    val name: String?,
    val parentId: Long?,
    val sku: String?,
    val unitId: Long?,
    val unitNumber: Double?
)
