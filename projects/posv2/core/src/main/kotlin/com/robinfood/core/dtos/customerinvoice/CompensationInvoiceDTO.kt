package com.robinfood.core.dtos.customerinvoice

data class CompensationInvoiceDTO(
    val compensationName: String,
    val copy: String,
    val currency: String,
    val title: String,
    val value: String
)
