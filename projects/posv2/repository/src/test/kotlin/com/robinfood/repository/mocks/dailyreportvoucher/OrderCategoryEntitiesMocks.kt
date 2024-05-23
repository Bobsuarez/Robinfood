package com.robinfood.repository.mocks.dailyreportvoucher

import com.robinfood.core.entities.dailyreportvoucher.OrderCategoryEntity

class OrderCategoryEntitiesMocks {
    val orderCategoryEntityMocks = listOf(
        OrderCategoryEntity(
            id = 1,
            name = "suggested",
            compensation = 100.00,
            grossValue = 100.00,
            discounts = 100.00,
            taxes = 100.00,
            netValue = 100.00
        ),
        OrderCategoryEntity(
            id = 2,
            name = "personalized",
            compensation = 200.00,
            grossValue = 300.00,
            discounts = 400.00,
            taxes = 500.00,
            netValue = 600.00
        )

    )

    val orderCategoryEntityIdNullMocks = listOf(
        OrderCategoryEntity(
            id = null,
            name = "personalized",
            compensation = 200.00,
            grossValue = 300.00,
            discounts = 400.00,
            taxes = 500.00,
            netValue = 600.00
        )

    )
}