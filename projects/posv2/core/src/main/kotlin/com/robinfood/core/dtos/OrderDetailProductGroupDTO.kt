package com.robinfood.core.dtos

data class OrderDetailProductGroupDTO (
        val id: Long,
        val name: String,
        val portions: List<OrderDetailProductGroupPortionDTO>,
        val removedPortions: List<OrderDetailGroupRemovedPortionDTO>?,
        var sku: String
)