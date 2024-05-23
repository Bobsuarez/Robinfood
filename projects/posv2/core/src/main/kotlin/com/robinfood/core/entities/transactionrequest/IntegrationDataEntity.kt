package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class IntegrationDataEntity(
    val addressCity: String? = null,
    val addressDescription: String? = null,
    val arrivalDate: String? = null,
    val arrivalTime: String? = null,
    val code: String,
    val integrationId: String?,
    val notes: String? = null,
    val orderType: Int? = null,
    val paymentMethod: String? = null,
    val totalTip: BigDecimal? = null,
    val userName: String,
    val userPhone: String? = null
)