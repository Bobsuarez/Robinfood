package com.robinfood.core.entities.transactionrequest

data class FlagsEntity(
    val corporate: CorporateFlagEntity?,
    val pits: PitsFlagEntity?,
    val submarine: SubmarineFlagEntity?,
    val togo: TogoFlagEntity?
)
