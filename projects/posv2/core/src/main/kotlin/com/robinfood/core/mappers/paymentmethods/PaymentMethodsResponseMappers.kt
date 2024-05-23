package com.robinfood.core.mappers.paymentmethods

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.paymentmethods.PaymentMethodResponseDTO
import com.robinfood.core.entities.paymentmethods.PaymentMethodResponseEntity

fun PaymentMethodResponseEntity.toPaymentMethodResponseDTO(): PaymentMethodResponseDTO? {
    return if (id == null) {
        return null
    } else PaymentMethodResponseDTO(
            id = id,
            image = image.orEmpty(),
            name = name.orEmpty(),
            originId = originId ?: DEFAULT_LONG_VALUE,
            slugName = slugName.orEmpty()
    )
}