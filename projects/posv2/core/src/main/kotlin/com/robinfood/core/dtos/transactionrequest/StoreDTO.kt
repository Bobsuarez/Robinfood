package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class StoreDTO (

    @NotNull
    val id: Long,

    @NotBlank
    val name: String,

    val posId: Long
)
