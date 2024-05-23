package com.robinfood.core.entities.customerinvoice

data class PrintFormatEntity(
    val company: CompanyInvoiceEntity,
    val order: OrderInvoiceEntity,
    val printer: PrintEntity
)
