package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.INTEGRATION_DEFAULT
import com.robinfood.core.dtos.transactionrequest.IntegrationDataDTO
import com.robinfood.core.entities.transactionrequest.IntegrationDataEntity

fun IntegrationDataDTO.toIntegrationDataEntity(): IntegrationDataEntity {
    return IntegrationDataEntity(
        addressCity,
        addressDescription,
        arrivalDate,
        arrivalTime,
        integrationId,
        INTEGRATION_DEFAULT,
        notes,
        orderType,
        paymentMethod,
        totalTip,
        userName,
        userPhone
    )
}