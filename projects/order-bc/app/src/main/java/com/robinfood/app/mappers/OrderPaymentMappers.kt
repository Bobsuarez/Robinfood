@file:JvmName("OrderPaymentMappers")

package com.robinfood.app.mappers

import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO
import com.robinfood.core.dtos.OrderPaymentDTO
import com.robinfood.core.dtos.OrderPaymentDetailDTO
import com.robinfood.core.entities.OrderPaymentEntity

fun OrderPaymentEntity.toOrderPaymentDTO(): OrderPaymentDTO {

    return OrderPaymentDTO(
        null,
        discount,
        id,
        orderId,
        originId,
        paymentMethodId,
        subtotal,
        tax,
        value
    )
}

fun OrderPaymentDTO.toGetOrderDetailPaymentMethodDTO(): GetOrderDetailPaymentMethodDTO {
    return GetOrderDetailPaymentMethodDTO(
        discount,
        paymentMethodId,
        subtotal,
        orderId,
        originId,
        tax,
        value
    );
}
