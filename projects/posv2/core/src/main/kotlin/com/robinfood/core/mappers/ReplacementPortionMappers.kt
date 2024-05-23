package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.ReplacementPortionDTO
import com.robinfood.core.entities.transactionrequest.ReplacementPortionEntity

fun ReplacementPortionDTO.toReplacementPortionEntity(): ReplacementPortionEntity {
    return ReplacementPortionEntity(
            id = id,
            name = name,
            product = product.toPortionProductEntity(),
            sku = sku,
            unitId = unitId,
            unitNumber = unitNumber
    )
}