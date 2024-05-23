package com.robinfood.app.mappers;

import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;
import com.robinfood.core.entities.PaymentMethodEntity;

public class GetOrderPaymentMethodsMapper {

    public static GetOrderPaymentMethodsDTO buildGetOrderPaymentMethodsDTO(
            PaymentMethodEntity paymentMethod,
            Long transactions,
            Double value
    ) {

        return GetOrderPaymentMethodsDTO.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .shortName(paymentMethod.getNameShort())
                .typeId(Long.valueOf(paymentMethod.getTypePaymentMethodId()))
                .transactions(transactions)
                .value(value)
                .build();
    }
}
