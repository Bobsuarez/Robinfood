package com.robinfood.core.dtos.userconfiguration

import javax.validation.constraints.NotNull

data class UserConfigurationResponseDTO(

    @NotNull(message = "Pos should not be null")
    val pos: PosResponseDTO,

    @NotNull(message = "Store should not be null")
    val store: StoreResponseDTO
)