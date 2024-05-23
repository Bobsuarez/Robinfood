package com.robinfood.core.dtos

data class OrderThirdPartyDTO(

    val documentNumber: String?,
    val documentType: Long?,
    val email: String?,
    val fullName: String?,
    val phone: String?
)
