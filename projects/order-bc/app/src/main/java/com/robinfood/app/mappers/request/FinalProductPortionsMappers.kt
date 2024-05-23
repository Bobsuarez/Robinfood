@file:JvmName("FinalProductPortionsMappers")
package com.robinfood.app.mappers.request

import com.robinfood.core.dtos.PortionProductDTO
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO
import com.robinfood.core.dtos.request.order.OriginalReplacementPortionDTO

fun FinalProductPortionDTO.toPortionProductDTO(): PortionProductDTO {
    return PortionProductDTO(
        product.name,
        product.id
    )
}

fun FinalProductPortionDTO.toOriginalReplacementPortionDTO(product : PortionProductDTO): OriginalReplacementPortionDTO {
    return OriginalReplacementPortionDTO(
        id,
        name,
        product,
        sku,
        unitId,
        unitNumber
    )
}