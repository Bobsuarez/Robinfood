@file:JvmName("OrderBrandHistoryMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO
import com.robinfood.core.entities.OrderBrandHistoryEntity

fun OrderBrandHistoryDTO.toOrderBrandHistoryEntity(): OrderBrandHistoryEntity{
    return OrderBrandHistoryEntity(
        brandId,
        null,
        null,
        orderId,
        orderStatusId,
        null,
        userId
    );
}
