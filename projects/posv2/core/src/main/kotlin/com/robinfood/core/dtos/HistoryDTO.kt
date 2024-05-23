package com.robinfood.core.dtos

data class HistoryDTO(
    val items: List<HistoryPaginatedItemDTO>?,
    val pagination: PropertyDTO?
)