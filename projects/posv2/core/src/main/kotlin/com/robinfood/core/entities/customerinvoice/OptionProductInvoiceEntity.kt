package com.robinfood.core.entities.customerinvoice

data class OptionProductInvoiceEntity(
    val name: String,
    val nameGroup: String,
    val price: Double,
    val quantity: Int,
    val symbol: String
)
