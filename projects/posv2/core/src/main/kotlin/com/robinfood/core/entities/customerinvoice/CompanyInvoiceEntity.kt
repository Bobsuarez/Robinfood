package com.robinfood.core.entities.customerinvoice

data class CompanyInvoiceEntity(
    val address: String,
    val city: String,
    val franchiseName: String,
    val id: Long,
    val logoTemplate: String,
    val name: String,
    val regime: String,
    val taxSymbol: String,
    val tax: String
)
