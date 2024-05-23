package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class BrandDTO (

    @NotNull
    @Positive
    val id: Long,

    @NotBlank
    val name: String
)