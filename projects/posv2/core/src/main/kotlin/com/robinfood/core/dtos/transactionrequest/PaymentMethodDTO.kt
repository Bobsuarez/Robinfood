package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PaymentMethodDTO(

    @NotNull
    @Positive
    var id: Long,

    @NotNull
    @Positive
    val originId: Long,

    @NotNull
    @Positive
    val value: BigDecimal,

    val detail: PaymentMethodDetailDTO?
)
