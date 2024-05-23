package com.robinfood.core.dtos.userconfiguration

import javax.validation.constraints.NotNull

data class StoreResponseDTO(

    @NotNull(message = "address should not be null")
    val address: String,

    @NotNull(message = "city should not be null")
    val city: String,

    @NotNull(message = "country should not be null")
    val country: String,

    @NotNull(message = "id should not be null")
    val id: Long,

    @NotNull(message = "internalName should not be null")
    val internalName: String,

    @NotNull(message = "name should not be null")
    val name: String,

    @NotNull(message = "timeZone should not be null")
    val timeZone: String,

    @NotNull(message = "uuid should not be null")
    val uuid: String

)