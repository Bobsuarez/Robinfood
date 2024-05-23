package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class TransactionUserDTO (

    @Email
    @NotBlank
    val email: String,

    @NotNull
    @Positive
    val id: Long,

    @NotBlank
    val lastName: String,

    @NotBlank
    val mobile: String,

    @NotBlank
    val firstName: String,

    @NotBlank
    val phoneCode: String
)