package com.robinfood.core.entities

import java.math.BigDecimal

data class OrderDetailProductTaxEntity(
        val familyTypeId: Int?,
        val id: Long?,
        val price: BigDecimal?,
        val value: BigDecimal?
)
