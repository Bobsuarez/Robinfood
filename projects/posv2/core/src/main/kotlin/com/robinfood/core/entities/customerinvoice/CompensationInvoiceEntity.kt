package com.robinfood.core.entities.customerinvoice

data class CompensationInvoiceEntity(
    val compensationName: String,
    val copy: String,
    val currency: String,
    val title: String,
    val value: String
)
