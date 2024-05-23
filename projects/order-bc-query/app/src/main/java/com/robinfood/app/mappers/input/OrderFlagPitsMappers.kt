@file:JvmName("OrderFlagPitsMappers")
package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;
import com.robinfood.core.entities.OrderFlagPitsEntity
import java.time.LocalDateTime


fun OrderFlagPitsDTO.toOrderFlagPitsEntity(): OrderFlagPitsEntity {
    return OrderFlagPitsEntity(
            carPlate,
            null,
            id,
            orderId
    )
}


fun OrderFlagPitsEntity.toOrderFlagPitsDTO(): OrderFlagPitsDTO {
    return OrderFlagPitsDTO(
        carPlate,
        id,
        null,
        orderId
    )
}