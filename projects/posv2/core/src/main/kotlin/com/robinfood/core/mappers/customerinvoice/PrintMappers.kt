package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.PrintDTO
import com.robinfood.core.entities.customerinvoice.PrintEntity

fun PrintEntity.toPrintDTO(): PrintDTO {
    return PrintDTO(
        ip = ip,
        deviceName = deviceName
    )
}