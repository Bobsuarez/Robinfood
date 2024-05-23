package com.robinfood.core.entities.customerinvoice

data class ServicesInvoiceEntity(
    val currency: String,
    val discount: DiscountInvoiceEntity,
    val name: String,
    val subtotal: SubTotalInvoiceEntity,
    val tax: TaxInvoiceEntity,
    val total: TotalInvoiceEntity
)
