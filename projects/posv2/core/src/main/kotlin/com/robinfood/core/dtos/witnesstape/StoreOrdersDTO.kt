package com.robinfood.core.dtos.witnesstape

data class StoreOrdersDTO(
    val compensation: Double,
    val discounts: Double,
    val grossValue: Double,
    val id: Long,
    val netValue: Double,
    val orderInvoiceNumber: String?,
    val posId: Long,
    val statusCode: String,
    val statusId: Long,
    val taxes: Double,
    val uuid: String
)
