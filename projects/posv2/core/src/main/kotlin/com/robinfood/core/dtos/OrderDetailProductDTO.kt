package com.robinfood.core.dtos

import com.robinfood.core.entities.OrderDetailProductArticleDTO
import java.math.BigDecimal

data class OrderDetailProductDTO(
        val article: OrderDetailProductArticleDTO,
        val basePrice: BigDecimal,
        val brand: OrderDetailBrandDTO,
        val category: OrderDetailCategory,
        val co2Total: BigDecimal,
        val discounts: List<OrderDetailProductDiscountDTO>,
        val displayType: Long?,
        val id: Long,
        val image: String,
        val name: String,
        val quantity: Int,
        val size: OrderDetailSizeDTO,
        var sku: String,
        val taxes: List<OrderDetailProductTaxDTO>,
        val unitPrice: BigDecimal
)