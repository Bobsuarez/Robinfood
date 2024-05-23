package com.robinfood.core.dtos.dailyreportvoucher

data class PaymentMethodListDTO (
    val name: String,
    val transactionsQuantity: Int,
    val value: Double
)