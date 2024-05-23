package com.robinfood.core.dtos

data class PropertyDTO(
    val perPage: Int?,
    val page: Int?,
    val lastPage: Int?,
    val total: Int?
)