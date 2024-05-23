package com.robinfood.core.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class OrderThirdPartyRequestDTO(

    @NotBlank(message = "Document number is mandatory")
    val documentNumber: String,

    @Positive(message = "Must be positive number")
    val documentType: Long,

    @NotBlank(message = "Email is mandatory")
    val email: String,

    @NotBlank(message = "fullName is mandatory")
    val fullName: String,

    val phone: String?
)
