package com.robinfood.core.dtos.userconfiguration

import javax.validation.constraints.NotNull

data class PosResponseDTO(

    @NotNull(message = "id should not be null")
    val id: Long,

    @NotNull(message = "name should not be null")
    val name: String,

    @NotNull(message = "currency should not be null")
    val currency: String,

    @NotNull(message = "countryId should not be null")
    val countryId: Long,

    @NotNull(message = "isDelivery should not be null")
    val isDelivery: Boolean,

    @NotNull(message = "flowId should not be null")
    val flowId: Long,

    @NotNull(message = "isMultiBrand should not be null")
    val isMultiBrand: Boolean
)