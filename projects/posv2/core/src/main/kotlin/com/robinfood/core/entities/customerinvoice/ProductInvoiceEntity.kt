package com.robinfood.core.entities.customerinvoice

data class ProductInvoiceEntity(
    val brandId: Long,
    val discount: Double,
    val name: String,
    val option: List<OptionProductInvoiceEntity>,
    val price: Double,
    val productId: Long,
    val quantity: Int,
    val subtotal: Double,
    val total: Double
)
