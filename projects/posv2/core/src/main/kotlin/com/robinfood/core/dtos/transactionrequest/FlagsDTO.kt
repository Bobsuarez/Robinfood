package com.robinfood.core.dtos.transactionrequest

import javax.validation.Valid

data class FlagsDTO (

    @Valid
    val corporate: CorporateFlagDTO?,

    @Valid
    val pits: PitsFlagDTO?,

    @Valid
    val submarine: SubmarineFlagDTO?,

    @Valid
    val togo: TogoFlagDTO?
)
