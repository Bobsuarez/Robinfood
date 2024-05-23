@file:JvmName("InputDiscountMappers")
package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.DiscountDTO
import com.robinfood.core.entities.OrderDiscountEntity

fun DiscountDTO.toOrderDiscountEntity(): OrderDiscountEntity {
    return OrderDiscountEntity(
        null,
        id,
        value,
        null,
        typeId,
        orderId,
        orderFinalProductId,
        null
    );
}