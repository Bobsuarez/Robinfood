package com.robinfood.core.entities.posrelatedtoastore

data class StorePosEntity(
    val id: Long,
    val name: String,
    val resolutions: List<ResolutionEntity>
)
