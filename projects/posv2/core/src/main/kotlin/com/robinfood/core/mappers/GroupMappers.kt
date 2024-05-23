package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.GroupDTO
import com.robinfood.core.entities.transactionrequest.GroupEntity

fun GroupDTO.toGroupEntity(): GroupEntity {
    return GroupEntity(
        id = id,
        name = name,
        portions = portions.map { portionDTO -> portionDTO.toPortionEntity() },
        sku = sku
    )
}