package com.robinfood.core.entities

data class HistoryEntity(
    val items: List<HistoryPaginatedItemEntity>?,
    val pagination: PropertyEntity?
)