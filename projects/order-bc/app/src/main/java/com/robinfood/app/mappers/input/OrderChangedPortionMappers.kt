@file:JvmName("InputOrderChangedPortionMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.ReplacementPortionDTO
import com.robinfood.core.entities.OrderChangedPortionEntity

fun ReplacementPortionDTO.toOrderChangedPortionEntity(): OrderChangedPortionEntity {
    return OrderChangedPortionEntity(
            id,
            name,
            product.id,
            product.name
    )
}