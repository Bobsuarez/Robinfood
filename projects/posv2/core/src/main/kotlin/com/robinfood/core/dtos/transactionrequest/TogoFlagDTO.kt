package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotNull

data class TogoFlagDTO (

    @NotNull
    val isActive: Boolean,

    @NotNull
    val statusId: Long
)
