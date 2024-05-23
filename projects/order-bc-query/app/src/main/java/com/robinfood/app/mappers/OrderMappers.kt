@file:JvmName("OrderMappers")

package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderDTO
import com.robinfood.core.dtos.OrderHistoryItemDTO
import com.robinfood.core.dtos.OrderStatusDTO
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO
import com.robinfood.core.entities.OrderEntity
import java.util.Collections

fun OrderEntity.toOrderCreateResultDTO(statusDTO: OrderStatusDTO?): ResponseCreatedOrderDTO {
    return ResponseCreatedOrderDTO(
        discounts,
        Collections.emptyList(),
        id,
        orderNumber,
        orderInvoiceNumber,
        statusDTO,
        subtotal,
        taxes,
        co2Total,
        total,
        uid,
        uuid
    )
}

fun OrderEntity.toOrderDTO(): OrderDTO {
    return OrderDTO(
        billingResolutionId,
        brandId,
        brandName,
        companyId,
        createdAt,
        currency,
        deliveryTypeId,
        discounts,
        id,
        localDate,
        localTime,
        numberFinalProducts,
        operationDate,
        orderInvoiceNumber,
        orderNumber,
        originId,
        originName,
        paid,
        pickupTime,
        posId,
        printed,
        statusId,
        storeId,
        storeName,
        subtotal,
        taxes,
        transactionId,
        co2Total,
        total,
        uid,
        uuid,
        userId,
        workshiftId
    )
}
