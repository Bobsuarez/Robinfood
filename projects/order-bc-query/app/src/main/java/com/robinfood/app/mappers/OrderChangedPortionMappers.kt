@file:JvmName("OrderChangedPortionMappers")

package com.robinfood.app.mappers

import com.robinfood.core.dtos.DetailChangedPortionDTO
import com.robinfood.core.dtos.OrderChangedPortionDTO
import com.robinfood.core.entities.OrderChangedPortionEntity

fun OrderChangedPortionEntity.toOrderChangedPortionDTO(): OrderChangedPortionDTO {
    return OrderChangedPortionDTO(
            changedPortionId,
            changedPortionName,
            changedProductId,
            changedProductName,
            createdAt,
            id,
            orderId,
            orderFinalProductPortionId,
            originalPortionId,
            originalPortionName,
            originalProductId,
            originalProductName
    )
}

fun OrderChangedPortionEntity.toDetailChangedPortionDTO(): DetailChangedPortionDTO {
        return DetailChangedPortionDTO(
            originalPortionId,
            originalPortionName,
            originalProductId
        )
}