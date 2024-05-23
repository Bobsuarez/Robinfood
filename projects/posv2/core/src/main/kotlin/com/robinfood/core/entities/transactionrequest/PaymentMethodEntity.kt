package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class PaymentMethodEntity(
    val id: Long,
    val originId: Long,
    val value: BigDecimal,
    val detail: () -> Unit
)
