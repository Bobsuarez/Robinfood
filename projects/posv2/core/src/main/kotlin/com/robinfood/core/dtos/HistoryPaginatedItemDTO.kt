package com.robinfood.core.dtos

import com.robinfood.core.dtos.transactionrequest.BrandDTO
import com.robinfood.core.dtos.transactionrequest.OriginDTO

data class HistoryPaginatedItemDTO(
        val brand: BrandDTO,
        val createdAt: String,
        val deliveryTypeId: Int,
        val id: Long,
        val orderInvoiceNumber: String,
        val orderNumber: String,
        val origin: OriginDTO,
        val total: String,
        val status: OrderStatusDTO,
        val user: OrderUserDTO
)
