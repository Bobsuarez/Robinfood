package com.robinfood.app.mappers;

import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.entities.PaymentMethodEntity;

import java.util.List;

public class GetOrderPaymentMappers {

    private GetOrderPaymentMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static GetOrderDetailPaymentMethodDTO orderPaymentDtoToGetOrderDetailPaymentMethodDTO(
            OrderPaymentDTO orderPaymentDTO,
            List<PaymentMethodEntity> paymentMethodEntities
    ) {

        return GetOrderDetailPaymentMethodDTO.builder()
                .discount(orderPaymentDTO.getDiscount())
                .id(orderPaymentDTO.getPaymentMethodId())
                .subtotal(orderPaymentDTO.getSubtotal())
                .name(getPaymentMethodName(paymentMethodEntities, orderPaymentDTO.getPaymentMethodId()))
                .orderId(orderPaymentDTO.getOrderId())
                .originId(orderPaymentDTO.getOriginId())
                .tax(orderPaymentDTO.getTax())
                .value(orderPaymentDTO.getValue())
                .build();
    }

    private static String getPaymentMethodName(List<PaymentMethodEntity> paymentMethodEntities, Long paymentMethodId) {
        return paymentMethodEntities.stream()
                .filter(paymentMethodEntity -> paymentMethodEntity.getId().equals(paymentMethodId))
                .map(PaymentMethodEntity::getName)
                .findFirst()
                .orElseThrow();
    }
}
