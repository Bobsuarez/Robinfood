package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;

public class GetPaymentMethodSaleDTOMappers {

    public static PaymentMethodSegmentDTO OrderSalesDTOToPaymentMethodSegmentDTO(
            Long idPaymentMethod,
            OrdersSalesSegmentDTO ordersSalesDTO) {

        return PaymentMethodSegmentDTO.builder()
                .idPayment(idPaymentMethod)
                .ordersSalesDTO(ordersSalesDTO)
                .build();
    }
}
