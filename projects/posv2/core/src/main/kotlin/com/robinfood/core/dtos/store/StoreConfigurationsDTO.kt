package com.robinfood.core.dtos.store

data class StoreConfigurationsDTO(
    val address: String?,
    val company: CompanyDTO,
    val id: Long,
    val name: String?,
    val timezone: String
)
