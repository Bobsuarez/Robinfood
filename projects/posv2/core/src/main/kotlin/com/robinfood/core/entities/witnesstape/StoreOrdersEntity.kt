package com.robinfood.core.entities.witnesstape

data class StoreOrdersEntity(
    val compensation: Double,
    val discounts: Double,
    val grossValue: Double,
    val id: Long?,
    val netValue: Double,
    val orderInvoiceNumber: String,
    val posId: Long,
    val statusCode: String,
    val statusId: Long,
    val taxes: Double,
    val uuid: String
)
