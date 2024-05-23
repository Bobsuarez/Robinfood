package com.robinfood.repository.mocks

import com.robinfood.core.entities.store.CompanyEntity
import com.robinfood.core.entities.store.CountryEntity
import com.robinfood.core.entities.store.StoreConfigurationsEntity

class StoreConfigurationsEntitiesMock {

    val countryEntity = CountryEntity(
        1,
        "Colombia"
    )

    val companyEntity = CompanyEntity(
        name = "Robinfood",
        country = countryEntity,
        identification = "900.000"
    )

    val storeConfigurationsEntityMock = StoreConfigurationsEntity(
        id = 1,
        address = "Cra 3",
        company = companyEntity,
        name = "Store 1",
        timezone = "America/Bogota"
    )

    val storeConfigurationsEntityIdNullMock = StoreConfigurationsEntity(
        id = null,
        address = "Cra 3",
        company = companyEntity,
        name = "Store 1",
        timezone = "America/Bogota"
    )
}