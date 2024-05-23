package com.robinfood.core.dtos.transactionrequest

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ArticleDTO (

    @NotNull
    @Positive
    val id: Long,

    @NotNull
    @Positive
    val menuHallProductId: Long,

    @NotNull
    @Positive
    val typeId: Long,

    @NotBlank
    val typeName: String
)
