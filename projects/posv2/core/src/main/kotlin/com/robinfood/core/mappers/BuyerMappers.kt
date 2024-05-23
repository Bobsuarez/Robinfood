package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.BuyerDTO
import com.robinfood.core.entities.transactionrequest.BuyerEntity

fun BuyerDTO.toBuyerEntity(): BuyerEntity {
    return BuyerEntity(
            identifier = identifier
    )
}