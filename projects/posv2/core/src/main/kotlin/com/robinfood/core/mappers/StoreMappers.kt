package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.StoreDTO
import com.robinfood.core.entities.transactionrequest.StoreEntity

fun StoreDTO.toStoreEntity(): StoreEntity {
    return StoreEntity(
        id = id,
        name = name,
        posId = posId
    )
}