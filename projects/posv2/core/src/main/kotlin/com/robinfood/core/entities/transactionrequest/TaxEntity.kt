package com.robinfood.core.entities.transactionrequest

import java.math.BigDecimal

data class TaxEntity(
    val dicTaxId: Long,
    val familyTaxTypeId: Long,
    val taxPrice: BigDecimal,
    val taxValue: BigDecimal
)
