package com.robinfood.core.dtos.transactionrequest

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class GroupDTO(

    @NotNull
    @Positive
    val id: Long,

    @NotBlank
    val name: String,

    @NotNull
    @Size
    @Valid
    val portions: List<PortionDTO>,

    @NotBlank
    val sku: String
)
