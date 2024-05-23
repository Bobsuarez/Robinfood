@file:JvmName("FinalProductPortionMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_EMPTY_VALUE
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO
import com.robinfood.core.entities.OrderFinalProductPortionEntity
import java.sql.Date

fun FinalProductPortionDTO.toOrderFinalProductPortionEntity(): OrderFinalProductPortionEntity {
    return OrderFinalProductPortionEntity(
            addition,
            price,
            companyId,
            replacementPortion != null,
            null,
            discount,
            unitId,
            effectiveSale,
            group.id,
            group.name,
            group.sku,
            DEFAULT_LONG_EMPTY_VALUE,
            Date(System.currentTimeMillis()).toString(),
            orderId,
            orderFinalProductId,
            id,
            name,
            sku,
            DEFAULT_INTEGER_VALUE,
            product.id,
            product.name,
            quantity,
            free,
            storeId,
            unitNumber,
            null
    )
}
