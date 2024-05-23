package com.robinfood.core.dtos.customerinvoice

data class OptionProductInvoiceDTO(
    val name: String,
    val nameGroup: String,
    val price: Double,
    val quantity: Int,
    val symbol: String
)
