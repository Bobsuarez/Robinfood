package com.robinfood.core.entities.userconfiguration

data class PosEntity(

    val id: Long?,

    val name: String?,

    val currency: String?,

    val countryId: Long?,

    val isDelivery: Boolean?,

    val flowId: Long?,

    val isMultiBrand: Boolean?
)