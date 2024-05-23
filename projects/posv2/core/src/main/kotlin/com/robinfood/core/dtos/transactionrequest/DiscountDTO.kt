package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class DiscountDTO (

    @NotNull
    val id: Long,

    @Min(0)
    @NotNull
    val value: BigDecimal
)
