package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class SizeDTO (

    @NotNull
    @Positive
    val id: Long,

    @NotNull
    val name: String
)
