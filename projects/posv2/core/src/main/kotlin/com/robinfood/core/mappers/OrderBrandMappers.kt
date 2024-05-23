package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.OrderBrandDTO
import com.robinfood.core.entities.transactionrequest.OrderBrandEntity

fun OrderBrandDTO.toOrderBrandEntity(): OrderBrandEntity {
    return OrderBrandEntity(
        id = id,
        name = name
    )
}