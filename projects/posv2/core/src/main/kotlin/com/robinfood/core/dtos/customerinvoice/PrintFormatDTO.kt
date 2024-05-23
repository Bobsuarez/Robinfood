package com.robinfood.core.dtos.customerinvoice

data class PrintFormatDTO(
    val company: CompanyInvoiceDTO,
    val order: OrderInvoiceDTO,
    val printer: PrintDTO
)
