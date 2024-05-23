package com.robinfood.app.mocks

import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO

class OrderGroupPaymentDTOMocks {

    val orderGroupPaymentDTOs = listOf(
        OrderGroupPaymentDTO(
            id = 1,
            name = "payment1",
            shortName = "payment1",
            transactions = 30,
            typeId = 1,
            value = 100.00
        ),
        OrderGroupPaymentDTO(
            id = 2,
            name = "payment2",
            shortName = "payment2",
            transactions = 30,
            typeId = 1,
            value = 100.00
        )
    )

    val orderGroupPaymentIdNullDTOs = listOf(
        OrderGroupPaymentDTO(
            id = null,
            name = "payment1",
            shortName = "payment1",
            transactions = 30,
            typeId = 1,
            value = 100.00
        )
    )
}