package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderThirdPartyRequestDTO
import com.robinfood.core.entities.transactionrequest.ThirdPartyEntity

fun OrderThirdPartyRequestDTO.toThirdPartyEntity(): ThirdPartyEntity {
    return ThirdPartyEntity(
        documentNumber = documentNumber,
        documentType = documentType,
        email = email,
        fullName = fullName,
        phone = phone,
    )
}