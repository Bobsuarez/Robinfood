@file:JvmName("OrderDiscountMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderDiscountDTO
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO
import com.robinfood.core.dtos.response.order.ResponseOrderDiscountDTO
import com.robinfood.core.entities.OrderDiscountEntity
import java.math.BigDecimal

fun OrderDiscountEntity.toOrderDiscountDTO(): OrderDiscountDTO {
    return OrderDiscountDTO(
        discountId,
        discountValue,
        id,
        orderDiscountTypeId,
        orderId,
        orderFinalProductId
    )
}

fun OrderDiscountEntity.toResponseOrderDiscountDTO(): ResponseOrderDiscountDTO {
    return ResponseOrderDiscountDTO(
            discountId,
            orderFinalProductId,
            id,
            orderDiscountTypeId,
            BigDecimal.valueOf(discountValue)
    )
}

fun OrderDiscountDTO.toGetOrderDetailDiscountDTO(): GetOrderDetailDiscountDTO {
    return GetOrderDetailDiscountDTO(
        id,
        orderId,
        orderFinalProductId,
        orderDiscountTypeId,
        discountValue
    )
}