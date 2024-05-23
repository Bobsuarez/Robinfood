package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.salesstore.OrdersPaymentDTO;
import com.robinfood.core.dtos.salesstore.SalesPaymentMethodDTO;
import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.dtos.salesstore.ValueSalesOrderDTO;

import java.math.BigDecimal;
import java.util.List;

public final class GetSalesStoresDTOMock {

    public static SalesStoresDTO getDataDefault() {

        return SalesStoresDTO.builder()
                .id(164L)
                .paymentMethods(List.of(SalesPaymentMethodDTO.builder()
                                .id(1L)
                                .orders(OrdersPaymentDTO.builder()
                                        .salesAWeekBefore(ValueSalesOrderDTO.builder()
                                                .value(BigDecimal.valueOf(1.0))
                                                .counter(1)
                                                .build())
                                        .salesCurrent(ValueSalesOrderDTO.builder()
                                                .value(BigDecimal.valueOf(1.0))
                                                .counter(1)
                                                .build())
                                        .build())
                        .build()))
                .build();
    }
}
