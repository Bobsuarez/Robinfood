package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailPaymentMethodEntity(
        val discount: BigDecimal?,
        val id: Long?,
        val originId: Long?,
        val subtotal: BigDecimal?,
        val tax: BigDecimal?,
        val value: BigDecimal?
)
