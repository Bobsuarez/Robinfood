@file:JvmName("OrderIntegrationMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderIntegrationDTO
import com.robinfood.core.entities.OrderIntegrationEntity

fun OrderIntegrationEntity.toOrderIntegrationDTO(): OrderIntegrationDTO {
    return OrderIntegrationDTO(
            addressCity,
            addressDescription,
            arrivalDate,
            arrivalTime,
            franchiseId,
            franchiseName,
            code,
            integrationId,
            notes,
            orderId,
            originId,
            originName,
            orderType,
            paymentMethod,
            storeId,
            storeName,
            totalDelivery,
            totalDiscount,
            totalOrder,
            totalTip,
            userName,
            userPhone
    );
}