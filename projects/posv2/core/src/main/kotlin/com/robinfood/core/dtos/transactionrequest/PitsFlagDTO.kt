package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PitsFlagDTO (

    @NotBlank
    val carPlate: String,

    @NotNull
    val isActive: Boolean
)
