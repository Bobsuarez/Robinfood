package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDetailPaymentMethodDTO (
        val discount: BigDecimal,
        val id: Long,
        val originId: Long,
        val subtotal: BigDecimal,
        val tax: BigDecimal,
        val value: BigDecimal
)