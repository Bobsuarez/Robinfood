package com.robinfood.core.dtos.store

data class CompanyDTO(
    val name: String?,
    val country: CountryDTO,
    val identification: String?
)
