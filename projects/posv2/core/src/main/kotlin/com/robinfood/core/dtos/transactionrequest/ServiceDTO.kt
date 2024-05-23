package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ServiceDTO (

    @NotNull
    @Positive
    val id: Long,

    @NotNull
    @Positive
    val value: BigDecimal
)
