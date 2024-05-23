package com.robinfood.core.entities

data class OrderThirdPartyEntity(
    val documentNumber: String?,
    val documentType: Long?,
    val email: String?,
    val fullName: String?,
    val phone: String?
)
