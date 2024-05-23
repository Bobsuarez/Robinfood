package com.robinfood.core.dtos

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class IntegrationDataDTO(
    val addressCity: String?,
    val addressDescription: String?,
    val arrivalDate: String?,
    val arrivalTime: String?,
    val code: Int?,

    @NotNull(message = "id should not be null")
    @Positive(message = "id should positive")
    val id: Int?,

    val notes: String?,

    @Min(value = 0L, message = "order type should min zero")
    val orderType: Int,

    val paymentMethod: String,

    @Min(value = 0L, message = "total tip should min zero")
    val totalTip: BigDecimal,

    @NotBlank(message = "username should not be empty")
    val userName: String,

    val userPhone: String
)