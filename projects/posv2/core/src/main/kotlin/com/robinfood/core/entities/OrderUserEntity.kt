package com.robinfood.core.entities

data class OrderUserEntity(
        val email: String?,
        val firstName: String?,
        val id: Long?,
        val lastName: String?,
        val loyaltyStatus: Long?,
        val mobile: String?
)
