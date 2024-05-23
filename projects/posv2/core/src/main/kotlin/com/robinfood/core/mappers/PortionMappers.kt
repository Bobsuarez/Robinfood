package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.PortionDTO
import com.robinfood.core.entities.transactionrequest.PortionEntity

fun PortionDTO.toPortionEntity(): PortionEntity {
    return PortionEntity(
        discount = discount,
        free = free,
        id = id,
        isIncluded = isIncluded,
        name = name,
        price = price,
        product = product.toPortionProductEntity(),
        quantity = quantity,
        replacementPortion = replacementPortion?.toReplacementPortionEntity(),
        sku = sku,
        unitId = unitId,
        unitNumber = unitNumber
    )
}