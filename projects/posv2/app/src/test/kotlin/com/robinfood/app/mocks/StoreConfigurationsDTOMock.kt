package com.robinfood.app.mocks

import com.robinfood.core.dtos.store.CompanyDTO
import com.robinfood.core.dtos.store.CountryDTO
import com.robinfood.core.dtos.store.StoreConfigurationsDTO

class StoreConfigurationsDTOMock {

    val countryDTO = CountryDTO(
        1,
        "Colombia"
    )

    val countryBrazilDTO = CountryDTO(
        3,
        "Brasil"
    )

    val companyDTO = CompanyDTO(
        name = "Robinfood",
        country = countryDTO,
        identification = "900.000"
    )

    val companyBrazilDTO = CompanyDTO(
        name = "Robinfood",
        country = countryBrazilDTO,
        identification = "900.000"
    )

    val storeConfigurationsDTOMock = StoreConfigurationsDTO(
        address = "Cra 3",
        company = companyDTO,
        id = 1L,
        name = "Store 1",
        timezone = "America/Bogota"
    )

    val storeConfigurationsDTOBrazilMock = StoreConfigurationsDTO(
        address = "Cra 3",
        company = companyBrazilDTO,
        id = 1L,
        name = "Store 1",
        timezone = "America/Bogota"
    )
}