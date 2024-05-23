package com.robinfood.core.dtos.store

data class StoreDeliveryPlatformDTO (
        val color: String,
        val flowId: Long,
        val id: Long,
        val imageUrl: String,
        val name: String,
        val slug: String,
        val status: Long
)
