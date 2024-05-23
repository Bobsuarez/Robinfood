@file:JvmName("UserDataMappers")

package com.robinfood.app.mappers

import com.robinfood.core.dtos.UserDataDTO
import com.robinfood.core.entities.OrderUserDataEntity

fun OrderUserDataEntity.toUserDataDTO(): UserDataDTO {
    return UserDataDTO(
            email,
            firstName,
            userId,
            lastName,
            mobile,
            orderId
    )
}