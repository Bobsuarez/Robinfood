package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.HistoryPaginatedItemDTO
import com.robinfood.core.dtos.PropertyDTO
import com.robinfood.core.entities.HistoryEntity
import com.robinfood.core.entities.HistoryPaginatedItemEntity
import com.robinfood.core.entities.PropertyEntity

fun HistoryEntity.toHistoryDTO(): HistoryDTO? {
    if(items == null){
        return null;
    }
    return HistoryDTO(
            items = items?.mapNotNull { historyPaginatedItemEntity ->
                historyPaginatedItemEntity.toHistoryPaginatedItemDTO()
            } ?: emptyList() ,
            pagination = pagination?.toPropertyDTO()
    )
}

fun HistoryPaginatedItemEntity.toHistoryPaginatedItemDTO(): HistoryPaginatedItemDTO {

    return HistoryPaginatedItemDTO(
            brand = brand.toBrandDTO(),
            createdAt = createdAt,
            deliveryTypeId = deliveryTypeId,
            id = id,
            orderInvoiceNumber = orderInvoiceNumber,
            orderNumber = orderNumber,
            origin = origin.toOriginDTO(),
            total = total,
            status = status.toOrderStatusDTO(),
            user = user.toOrderStatusDTO()
    )
}

fun PropertyEntity.toPropertyDTO(): PropertyDTO {
    return PropertyDTO(
        perPage = perPage ?: DEFAULT_INTEGER_VALUE,
        page = page ?: DEFAULT_INTEGER_VALUE,
        lastPage = lastPage ?: DEFAULT_INTEGER_VALUE,
        total = total ?: DEFAULT_INTEGER_VALUE
    )
}