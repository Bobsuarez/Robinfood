package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.dtos.OrderDetailProductTaxDTO
import com.robinfood.core.entities.OrderDetailProductTaxEntity
import java.math.BigDecimal

fun OrderDetailProductTaxEntity.toOrderDetailProductTaxDTO(): OrderDetailProductTaxDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailProductTaxDTO(
                familyTypeId = familyTypeId ?: DEFAULT_INTEGER_VALUE,
                id = id,
                price = price ?: BigDecimal.ZERO,
                value = value ?: BigDecimal.ZERO
        )
    }
}