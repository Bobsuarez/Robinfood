package com.robinfood.app.mocks

import com.robinfood.core.dtos.OrderDailyDTO
import java.math.BigDecimal

class OrderDailyDTOsMocks {

    val orderDailyDTO = OrderDailyDTO(
        id = 5466461L,
        orderNumber = "0000",
        invoiceNumber = "0000",
        totalPrice = BigDecimal.valueOf(75600),
        deliveryTypeId = 1,
        originId = 4L,
        origin = "Autogestion v2.0",
        originBackgroundColor =  "0070C0",
        brandId = 4L,
        brandImage = "https://assets.robinfood.com/delivery/menu/brands/976fd474-cc15-4f7b-b4ac-1f91e75ce7de.png",
        brandBackgroundColor = "#f5A800",
        userId = 711747L,
        customerName = "SAUL OSORIO",
        creationTime = "2022-12-01T13:33:53"
    )

    val orderDailyDTOResult = OrderDailyDTO(
        id = 5466461L,
        orderNumber = "0000",
        invoiceNumber = "0000",
        totalPrice = BigDecimal.valueOf(75600),
        deliveryTypeId = 1,
        originId = 4L,
        origin = "Autogestion v2.0",
        originBackgroundColor =  "0070C0",
        brandId = 4L,
        brandImage = "https://assets.robinfood.com/delivery/menu/brands/976fd474-cc15-4f7b-b4ac-1f91e75ce7de.png",
        brandBackgroundColor = "#f5A800",
        userId = 711747L,
        customerName = "SAUL OSORIO",
        creationTime = "2022-12-01T13:33:53"
    )
}