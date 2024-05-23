package com.robinfood.core.dtos.customerinvoice

data class ProductInvoiceDTO(
    val brandId: Long,
    val discount: Double,
    val name: String,
    val option: List<OptionProductInvoiceDTO>,
    val price: Double,
    val productId: Long,
    val quantity: Int,
    val subtotal: Double,
    val total: Double
)
