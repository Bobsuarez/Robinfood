package com.robinfood.core.entities.transactionrequest

data class DeviceEntity(
    val ip: String,
    val platform: Int,
    val timezone: String,
    val version: String
)
