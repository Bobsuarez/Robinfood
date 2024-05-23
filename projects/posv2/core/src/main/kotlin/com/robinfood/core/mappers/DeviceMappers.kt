package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.DeviceDTO
import com.robinfood.core.entities.transactionrequest.DeviceEntity

fun DeviceDTO.toDeviceEntity(): DeviceEntity {
    return DeviceEntity(
        ip = ip,
        platform = platform,
        timezone = timezone,
        version = version
    )
}