package com.robinfood.core.entities.menu

import java.math.BigDecimal

data class MenuGroupPortionEntity(
    val default: Boolean?,
    val discount: BigDecimal?,
    val id: Long?,
    val image: String?,
    val name: String?,
    val parentId: Long?,
    val premiumPrice: BigDecimal?,
    val price: BigDecimal?,
    val position: Int?,
    val sku: String?,
    val unit: Long?,
    val weight: Double?
)