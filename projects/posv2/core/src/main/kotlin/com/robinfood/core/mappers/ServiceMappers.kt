package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.ServiceDTO
import com.robinfood.core.entities.transactionrequest.ServiceEntity

fun ServiceDTO.toServiceEntity(): ServiceEntity {
    return ServiceEntity(
        id = id,
        value = value
    )
}