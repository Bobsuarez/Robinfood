package com.robinfood.core.dtos.flags

data class FlagsDTO(
    val countryId: Long,
    val featureFlags: List<FeatureFlagsDTO>,
    val platformId: Long?
){
    constructor() : this(0, emptyList(), 0)
}
