package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class TaxDTO (

    @NotNull
    @Positive
    val dicTaxId: Long,

    @NotNull
    @Positive
    val familyTaxTypeId: Long,

    @NotNull
    @Positive
    val taxPrice: BigDecimal,

    @NotNull
    @Positive
    val taxValue: BigDecimal
)
