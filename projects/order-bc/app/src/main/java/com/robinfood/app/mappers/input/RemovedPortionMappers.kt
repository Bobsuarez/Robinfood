@file:JvmName("FinalProductRemovedPortionMappers")
package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO
import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO
import com.robinfood.core.entities.FinalProductRemovedPortionEntity

fun FinalProductRemovedPortionEntity.toFinalProductRemovedPortionDTO(): OrderDetailRemovedPortionDTO {
    return OrderDetailRemovedPortionDTO(
        portionId,
        groupId,
        portionName,
        orderFinalProductId
    )
}

fun FinalProductRemovedPortionDTO.toFinalProductRemovedPortionEntity(): FinalProductRemovedPortionEntity {
    return FinalProductRemovedPortionEntity(
        finalProductId!!,
        groupId,
        orderId!!,
        orderFinalProductId!!,
        id,
        portionName
    )
}