package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.CompanyDTO
import com.robinfood.core.entities.transactionrequest.CompanyEntity

fun CompanyDTO.toCompanyEntity(): CompanyEntity {
    return CompanyEntity(
        id = id,
        currency = currency
    )
}