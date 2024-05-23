package com.robinfood.repository.mocks.dailyreportvoucher

import com.robinfood.core.entities.dailyreportvoucher.OrderGroupPaymentEntity

class OrderGroupPaymentEntitiesMocks {

    val orderGroupPaymentEntities = listOf(
        OrderGroupPaymentEntity(
            id = 1L,
            name = "payment1",
            shortName = "payment1",
            transactions = 30,
            typeId = 1,
            value = 100.00
        ),
        OrderGroupPaymentEntity(
            id = 2L,
            name = "payment2",
            shortName = "payment2",
            transactions = 30,
            typeId = 1,
            value = 100.00
        )
    )

    val orderGroupPaymentIdNullEntities = listOf(
        OrderGroupPaymentEntity(
            id = null,
            name = "payment2",
            shortName = "payment2",
            transactions = 30,
            typeId = 1,
            value = 100.00
        )
    )
}