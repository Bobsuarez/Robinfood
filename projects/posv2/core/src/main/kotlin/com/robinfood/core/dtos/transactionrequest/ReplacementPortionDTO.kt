package com.robinfood.core.dtos.transactionrequest

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class ReplacementPortionDTO (

    @NotNull
    @Positive
    val id: Long,

    @NotBlank
    val name: String,

    @NotNull
    @Valid
    val product: PortionProductDTO,

    @NotBlank
    val sku: String,

    @NotNull
    @Positive
    val unitId: Long,

    @NotNull
    @Positive
    val unitNumber: Double
)
