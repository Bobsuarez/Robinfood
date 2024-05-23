package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CompanyDTO (

    @NotBlank(message = "Company currency should not be null")
    val currency: String,

    @NotNull(message = "Company id should not be null")
    val id: Long
)
