package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

data class PortionDTO (

    @NotNull
    @Min(0)
    val discount: BigDecimal,

    @PositiveOrZero
    val free: Int,

    @NotNull
    @Positive
    val id: Long,

    @NotNull
    val isIncluded: Boolean,

    @NotBlank
    val name: String,

    @NotNull
    @Min(0)
    val price: BigDecimal,

    @NotNull
    @Valid
    val product: PortionProductDTO,

    @NotNull
    @Positive
    val quantity: Int,

    @Valid
    val replacementPortion: ReplacementPortionDTO?,

    @NotBlank
    val sku: String,

    @NotNull
    @Positive
    val unitId: Long,

    @NotNull
    @Positive
    val unitNumber: Double
)
