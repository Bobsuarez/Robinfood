@file:JvmName("PaymentMethodMappers")
package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO
import com.robinfood.core.entities.PaymentEntity

fun RequestPaymentMethodDTO.toPaymentEntity(transactionId: Long): PaymentEntity {
    return PaymentEntity(
        null,
        transactionId,
        value,
        id,
        originId
    );
}
