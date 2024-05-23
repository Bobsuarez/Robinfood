package com.robinfood.app.mocks.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity

class StoreOrdersDTOMocks {

    val storeOrdersDTOsMocks = listOf(
        StoreOrdersDTO(
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
        StoreOrdersDTO(
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