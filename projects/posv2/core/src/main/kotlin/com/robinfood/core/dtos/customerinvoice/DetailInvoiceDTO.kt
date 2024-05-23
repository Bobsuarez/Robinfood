package com.robinfood.core.dtos.customerinvoice

data class DetailInvoiceDTO(
    val currency: String,
    val discount: DiscountInvoiceDTO,
    val subtotal: SubTotalInvoiceDTO,
    val tax: TaxInvoiceDTO,
    val title: String,
    val total: TotalInvoiceDTO,
    val typeTax: TypeTaxInvoiceDTO
)
