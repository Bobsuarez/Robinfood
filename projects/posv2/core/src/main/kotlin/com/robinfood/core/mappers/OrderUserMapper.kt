package com.robinfood.core.mappers

import com.robinfood.core.dtos.OrderUserDTO
import com.robinfood.core.entities.OrderUserEntity

fun OrderUserEntity.toOrderStatusDTO(): OrderUserDTO {
    return OrderUserDTO(
            email = email,
            firstName = firstName,
            id = id,
            lastName = lastName,
            loyaltyStatus = loyaltyStatus,
            mobile = mobile
    )
}
