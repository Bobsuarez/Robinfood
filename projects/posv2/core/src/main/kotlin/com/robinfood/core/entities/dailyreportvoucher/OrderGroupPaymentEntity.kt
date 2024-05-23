package com.robinfood.core.entities.dailyreportvoucher

data class OrderGroupPaymentEntity(
    val id: Long?,
    val name: String,
    val shortName: String,
    val transactions: Int,
    val typeId: Long,
    val value: Double
)