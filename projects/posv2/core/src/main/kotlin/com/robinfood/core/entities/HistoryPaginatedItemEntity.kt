package com.robinfood.core.entities;

import com.robinfood.core.entities.transactionrequest.BrandEntity
import com.robinfood.core.entities.transactionrequest.OriginEntity

data class HistoryPaginatedItemEntity (
        val brand: BrandEntity,
        val createdAt: String,
        val deliveryTypeId: Int,
        val id: Long,
        val orderInvoiceNumber: String,
        val orderNumber: String,
        val origin: OriginEntity,
        val total: String,
        val status: OrderStatusEntity,
        val user:OrderUserEntity
)
