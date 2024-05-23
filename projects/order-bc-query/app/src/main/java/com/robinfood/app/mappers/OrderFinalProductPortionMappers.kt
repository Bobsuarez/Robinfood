@file:JvmName("OrderFinalProductPortionMappers")

package com.robinfood.app.mappers

import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO
import com.robinfood.core.entities.OrderFinalProductPortionEntity
import java.math.BigDecimal

fun OrderFinalProductPortionEntity.toGetOrderDetailFinalProductPortionDTO(): GetOrderDetailFinalProductPortionDTO {
    return GetOrderDetailFinalProductPortionDTO(
        addition,
        null,
        BigDecimal(discount),
        orderFinalProductId,
        groupId,
        groupName,
        portionId,
        portionName,
        orderId,
        basePrice,
        productId,
        quantity,
        quantityFree,
        portionSku,
        dicUnitId,
        unitsNumber
    )
}
