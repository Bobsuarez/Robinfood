package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.RemovedPortionDTO
import com.robinfood.core.entities.transactionrequest.RemovedPortionEntity

fun RemovedPortionDTO.toRemovedPortionEntity(): RemovedPortionEntity {
    return RemovedPortionEntity(
        groupId = groupId,
        id = id,
        portionName = portionName
    )
}