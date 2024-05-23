package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.SizeDTO
import com.robinfood.core.entities.transactionrequest.SizeEntity

fun SizeDTO.toSizeEntity(): SizeEntity {
    return SizeEntity(
        id = id,
        name = name
    )
}