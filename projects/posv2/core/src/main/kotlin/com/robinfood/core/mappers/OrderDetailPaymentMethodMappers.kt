package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity
import java.math.BigDecimal

fun OrderDetailPaymentMethodEntity.toOrderDetailPaymentMethodDTO(): OrderDetailPaymentMethodDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailPaymentMethodDTO(
                discount = discount ?: BigDecimal.ZERO,
                id = id,
                originId = originId ?: DEFAULT_LONG_VALUE,
                subtotal = subtotal ?: BigDecimal.ZERO,
                tax = tax ?: BigDecimal.ZERO,
                value = value ?: BigDecimal.ZERO
        )
    }
}