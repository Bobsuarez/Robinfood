package com.robinfood.core.entities

data class APIResponseEntity<T>(
    val code: Int?,
    val data: T?,
    val locale: String?,
    val message: String?,
    val status: String?
)