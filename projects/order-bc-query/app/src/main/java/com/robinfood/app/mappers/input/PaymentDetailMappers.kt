@file:JvmName("PaymentDetailMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO
import com.robinfood.core.entities.OrderPaymentDetailEntity

fun RequestPaymentDetailDTO.toOrderPaymentDetailEntity(): OrderPaymentDetailEntity {
    return OrderPaymentDetailEntity(
            accountType,
            additionalInformation,
            approvalCode,
            null,
            franchiseType,
            null,
            orderId,
            orderPaymentId,
            posTerminalCode,
            providerId,
            selfManagementCode,
            uniqueTransactionId,
            null
    )
}