package com.robinfood.core.dtos.customerinvoice

data class ServiceReportDTO(
    val name: String,
    val itemsService: List<ItemsServiceDTO>
)
