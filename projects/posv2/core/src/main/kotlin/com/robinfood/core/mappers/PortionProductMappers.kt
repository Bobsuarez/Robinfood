package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.PortionProductDTO
import com.robinfood.core.entities.transactionrequest.PortionProductEntity

fun PortionProductDTO.toPortionProductEntity(): PortionProductEntity {
    return PortionProductEntity(
        id = id,
        name = name
    )
}