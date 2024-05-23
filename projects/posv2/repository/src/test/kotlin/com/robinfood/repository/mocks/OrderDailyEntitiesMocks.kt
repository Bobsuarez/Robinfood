package com.robinfood.repository.mocks

import com.robinfood.core.entities.orderdaily.OrderBrandDailyEntity
import com.robinfood.core.entities.orderdaily.OrderDailyEntity
import com.robinfood.core.entities.orderdaily.OrderDailyUserEntity
import com.robinfood.core.entities.orderdaily.OrderOriginDailyEntity
import java.math.BigDecimal

class OrderDailyEntitiesMocks {

    private val brandDTO = OrderBrandDailyEntity(
        color = "#e30a33",
        image = "https://assets.robinfood.com/delivery/menu/brands/1af41496-aef5-48c7-910a-bfab3ed4fa11.png",
        name = "MUY"
    )

    private val  originDTO =  OrderOriginDailyEntity(
        color = "1DA06A",
        id = 2L,
        name = "Aplicaciones"
    )

    private val userDTO = OrderDailyUserEntity(
        email = "alonzo@robinfood.com",
        firstName = "Alonzo",
        id = 282671L,
        lastName = "Herrera",
        loyaltyStatus = null,
        mobile = "3030202202"
    )

    val orderDailyEntity = OrderDailyEntity(
        brand = brandDTO,
        brandId = 1L,
        createdAt = "2022-12-01T16:03:26",
        deliveryTypeId = 1,
        id = 5466476L,
        orderInvoiceNumber = "0000",
        orderNumber = "0000",
        origin = originDTO,
        total = BigDecimal.valueOf(11900.0),
        user = userDTO
    )
}