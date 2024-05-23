package com.robinfood.core.entities.store

data class StoreConfigurationsEntity(
    val address: String?,
    val company: CompanyEntity,
    val id: Long?,
    val name: String?,
    val timezone: String
)
