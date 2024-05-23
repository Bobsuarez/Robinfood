package com.robinfood.core.entities.orderdaily

data class OrderDailyUserEntity(
    val email: String?,
    val firstName: String?,
    val id: Long,
    val lastName: String?,
    val loyaltyStatus: Long?,
    val mobile: String?
)
