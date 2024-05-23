package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.PaymentMethodDTO
import com.robinfood.core.entities.transactionrequest.PaymentMethodEntity

fun PaymentMethodDTO.toPaymentMethodEntity(): PaymentMethodEntity {
    return PaymentMethodEntity(
        id = id,
        originId = originId,
        value = value
    ) {}
}