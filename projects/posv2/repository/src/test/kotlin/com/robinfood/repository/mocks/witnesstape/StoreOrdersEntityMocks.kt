package com.robinfood.repository.mocks.witnesstape

import com.robinfood.core.entities.witnesstape.StoreOrdersEntity

class StoreOrdersEntityMocks {

    val storeOrdersEntities = listOf(
        StoreOrdersEntity(
            compensation = 100.00,
            discounts = 20.00,
            grossValue = 10.00,
            id = 1L,
            netValue = 80.00,
            orderInvoiceNumber = "20090",
            1L,
            "ORDER_CREATED",
            1L,
            taxes = 100.00,
            uuid = "zyx"
        ),
        StoreOrdersEntity(
            compensation = 100.00,
            discounts = 20.00,
            grossValue = 10.00,
            id = 1L,
            netValue = 80.00,
            orderInvoiceNumber = "20090",
            1L,
            "ORDER_CREATED",
            1L,
            taxes = 100.00,
            uuid = "zyx"
        )
    )

    val storeOrdersEntitiesIdNull = listOf(
        StoreOrdersEntity(
            compensation = 100.00,
            discounts = 20.00,
            grossValue = 10.00,
            id = null,
            netValue = 80.00,
            orderInvoiceNumber = "20090",
            1L,
            "ORDER_CREATED",
            1L,
            taxes = 100.00,
            uuid = "zyx"
        )
    )
}