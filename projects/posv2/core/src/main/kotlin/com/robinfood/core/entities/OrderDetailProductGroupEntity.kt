package com.robinfood.core.entities

data class OrderDetailProductGroupEntity(
        val id: Long?,
        val name: String?,
        val portions: List<OrderDetailProductGroupPortionEntity>?,
        val removedPortions: List<OrderDetailGroupRemovedPortionEntity>?,
        val sku: String?
)
