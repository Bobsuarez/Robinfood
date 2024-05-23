package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderDailyDTO
import com.robinfood.core.entities.orderdaily.OrderDailyEntity
import java.math.BigDecimal

fun OrderDailyEntity.toOrderDailyDTO(): OrderDailyDTO?{
    return if (id == null) {
        null
    } else {
        return OrderDailyDTO(
            id = id,
            orderNumber = orderNumber.orEmpty(),
            invoiceNumber = orderInvoiceNumber.orEmpty(),
            totalPrice = total ?: BigDecimal.ZERO,
            deliveryTypeId = deliveryTypeId,
            originId = origin.id,
            origin = origin.name,
            originBackgroundColor = origin.color,
            brandId = brandId,
            brandImage = brand.image,
            brandBackgroundColor = brand.color,
            userId = user.id,
            customerName = user.firstName +" "+ user.lastName,
            creationTime = createdAt
        )
    }
}