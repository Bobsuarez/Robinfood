@file:JvmName("OrderIntegrationMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderIntegrationDTO
import com.robinfood.core.entities.OrderIntegrationEntity


fun OrderIntegrationDTO.toOrderIntegrationEntity(): OrderIntegrationEntity {
    return OrderIntegrationEntity(
            addressCity,
            addressDescription,
            arrivalDate,
            arrivalTime,
            code,
            null,
            brandId,
            brandName,
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
            null,
            userName,
            userPhone
    );
}