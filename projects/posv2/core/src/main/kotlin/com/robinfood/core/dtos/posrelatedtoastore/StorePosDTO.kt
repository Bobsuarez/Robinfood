package com.robinfood.core.dtos.posrelatedtoastore

data class StorePosDTO(
    val id: Long,
    val name: String,
    val resolutions: List<ResolutionDTO>
)
