package com.robinfood.app.mocks;

import com.robinfood.core.dtos.orderSales.OrderActiveSalesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.orderSales.SalesActiveAWeekBeforeDTO;
import com.robinfood.core.dtos.orderSales.SalesActiveCurrentDTO;

import java.math.BigDecimal;
import java.util.List;

public class ResponseOrderActiveSalesDTOMock {

    public static ResponseOrderActiveSalesDTO getDataDefault() {
        return ResponseOrderActiveSalesDTO.builder()
                .companies(List.of(OrderSalesByCompanyDTO.builder()
                        .orders(OrderActiveSalesDTO.builder()
                                .salesCurrent(SalesActiveCurrentDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .salesAWeekBefore(SalesActiveAWeekBeforeDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .build())
                        .id(1L)
                        .build()))
                .build();
    }
}
