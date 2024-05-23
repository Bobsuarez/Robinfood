package com.robinfood.app.mocks

import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO

class OrderCategoryDTOsMocks {
    val orderCategoryEntityMocks = listOf(
        OrderCategoryDTO(
            id = 1,
            name = "suggested",
            compensation = 100.00,
            grossValue = 100.00,
            discounts = 100.00,
            taxes = 100.00,
            netValue = 100.00
        ),
        OrderCategoryDTO(
            id = 2,
            name = "suggested",
            compensation = 100.00,
            grossValue = 100.00,
            discounts = 100.00,
            taxes = 100.00,
            netValue = 100.00
        )
    )

    val orderCategoryEntityNullMocks = listOf(
        OrderCategoryDTO(
            id = null,
            name = "suggested",
            compensation = 100.00,
            grossValue = 100.00,
            discounts = 100.00,
            taxes = 100.00,
            netValue = 100.00
        )
    )
}