package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailProductEntity(
        val articleId: Long?,
        val articleName: String?,
        val articleTypeId: Long?,
        val basePrice: BigDecimal?,
        val brandMenuId: Long?,
        val brandName: String?,
        val categoryId: Long?,
        val categoryName: String?,
        val co2Total: BigDecimal?,
        val discounts: List<OrderDetailProductDiscountEntity>?,
        val displayType: Long?,
        val groups: List<OrderDetailProductGroupEntity>?,
        val id: Long?,
        val image: String?,
        val menuHallProductId: Long?,
        val name: String?,
        val quantity: Int?,
        val sizeId: Long?,
        val sizeName: String?,
        val sku: String?,
        val taxes: List<OrderDetailProductTaxEntity>?,
        val unitPrice: BigDecimal?
)
