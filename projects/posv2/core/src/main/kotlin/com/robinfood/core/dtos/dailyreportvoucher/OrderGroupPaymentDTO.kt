package com.robinfood.core.dtos.dailyreportvoucher

data class OrderGroupPaymentDTO(
    val id: Long?,
    val name: String,
    val shortName: String,
    val transactions: Int,
    val typeId: Long,
    val value: Double
)