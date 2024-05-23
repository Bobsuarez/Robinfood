package com.robinfood.core.entities.transactionrequest

data class ThirdPartyEntity(
    val documentNumber: String?,
    val documentType: Long?,
    val email: String?,
    val fullName: String?,
    val phone: String?
)
