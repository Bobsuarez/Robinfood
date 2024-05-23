package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebystore.CountAndValueByOrdersDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;

import java.math.BigDecimal;

public class OutItemsByPaymentMethodsDTOMock {

    public static PaymentMethodsOfStoreDTO getDefault() {

        return PaymentMethodsOfStoreDTO.builder().
                orders(CountAndValueByOrdersDTO.builder()
                        .salesAWeekBefore(
                                DataGenericSegmentDTO.builder().value(BigDecimal.valueOf(2000.0)).build())
                        .salesCurrent(
                                DataGenericSegmentDTO.builder().value(BigDecimal.valueOf(2000.0)).build())
                        .build()).build();
    }
}
