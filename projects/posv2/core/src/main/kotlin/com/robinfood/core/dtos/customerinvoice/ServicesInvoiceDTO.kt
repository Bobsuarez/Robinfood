package com.robinfood.core.dtos.customerinvoice

data class ServicesInvoiceDTO(
    val currency: String,
    val name: String,
    val discount: DiscountInvoiceDTO,
    val subtotal: SubTotalInvoiceDTO,
    val tax: TaxInvoiceDTO,
    val total: TotalInvoiceDTO
)
