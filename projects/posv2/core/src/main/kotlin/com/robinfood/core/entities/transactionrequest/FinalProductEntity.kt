package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class FinalProductEntity(
    val article: ArticleEntity,
    val brand: BrandEntity,
    val co2Total: BigDecimal,
    val discounts: List<DiscountEntity>,
    val groups: List<GroupEntity>,
    val id: Long,
    val image: String,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    val removedPortions: List<RemovedPortionEntity>,
    val size: SizeEntity,
    val sku: String,
    val taxes: List<TaxEntity>,
    val totalPrice: BigDecimal
)
