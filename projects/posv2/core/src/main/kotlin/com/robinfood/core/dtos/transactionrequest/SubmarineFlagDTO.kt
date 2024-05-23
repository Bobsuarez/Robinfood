package com.robinfood.core.dtos.transactionrequest

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class SubmarineFlagDTO(

    @JsonProperty("isActive")
    @NotNull
    val isActive: Boolean
)
