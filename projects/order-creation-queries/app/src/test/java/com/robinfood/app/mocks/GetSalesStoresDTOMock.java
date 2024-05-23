package com.robinfood.app.mocks;

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
                                                .counter(1)
                                                .value(BigDecimal.valueOf(1.0))
                                                .build())
                                        .salesCurrent(ValueSalesOrderDTO.builder()
                                                .counter(1)
                                                .value(BigDecimal.valueOf(1.0))
                                                .build())
                                        .build())
                        .build()))
                .build();
    }
}
