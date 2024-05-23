package com.robinfood.core.entities.customerinvoice

data class DetailInvoiceEntity(
    val currency: String,
    val discount: DiscountInvoiceEntity,
    val subtotal: SubTotalInvoiceEntity,
    val tax: TaxInvoiceEntity,
    val title: String,
    val total: TotalInvoiceEntity,
    val typeTax: TypeTaxInvoiceEntity
)
