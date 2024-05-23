package com.robinfood.core.dtos

import java.math.BigDecimal

data class OrderDetailProductTaxDTO (
        val familyTypeId: Int,
        val id: Long,
        val price: BigDecimal,
        val value: BigDecimal
)