package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class DeviceDTO (

    @NotBlank
    val ip: String,

    @NotNull
    val platform: Int,

    @NotBlank
    val timezone: String,

    @NotBlank
    val version: String
)
