package com.robinfood.core.dtos.transactionrequest

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class IntegrationDataDTO(

    val addressCity: String? = null,

    val addressDescription: String? = null,

    val arrivalDate: String? = null,

    val arrivalTime: String? = null,

    val code: Int? = null,

    @NotNull
    val integrationId: String,

    val notes: String? = null,

    @Min(0)
    val orderType: Int? = null,

    val paymentMethod: String? = null,

    @Min(0)
    val totalTip: BigDecimal? = null,

    @NotBlank
    val userName: String,

    val userPhone: String? = null
)