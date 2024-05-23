package com.robinfood.core.mappers.store

import com.robinfood.core.dtos.store.CompanyDTO
import com.robinfood.core.dtos.store.CountryDTO
import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.entities.store.CompanyEntity
import com.robinfood.core.entities.store.CountryEntity
import com.robinfood.core.entities.store.StoreConfigurationsEntity

fun CountryEntity.toCountryDTO(): CountryDTO {
    return CountryDTO(
        id = id,
        name = name
    )
}

fun CompanyEntity.toCompanyDTO(): CompanyDTO {
    return CompanyDTO(
        name = name,
        identification = identification,
        country = country.toCountryDTO()
    )
}

fun StoreConfigurationsEntity.toStoreConfigurationDTO(): StoreConfigurationsDTO? {
    return if (id == null) {
        return null
    } else StoreConfigurationsDTO(
        address = address.orEmpty(),
        company = company.toCompanyDTO(),
        id = id,
        name = name,
        timezone = timezone
    )
}