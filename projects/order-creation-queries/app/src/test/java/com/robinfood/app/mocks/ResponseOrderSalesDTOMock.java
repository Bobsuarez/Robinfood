package com.robinfood.app.mocks;

import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.OrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesAWeekBeforeDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesCurrentDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalCompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalSalesAWeekBeforeDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalSalesCurrentDTO;

import java.math.BigDecimal;
import java.util.List;

public class ResponseOrderSalesDTOMock {

    public static ResponseOrderSalesDTO getDataDefault() {
        return ResponseOrderSalesDTO.builder()
                .companies(List.of(CompaniesOrderSalesDTO.builder()
                        .orders(OrderSalesDTO.builder()
                                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .salesCurrent(SalesCurrentDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .salesAWeekBefore(SalesAWeekBeforeDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .build())
                        .id(1L)
                        .name("name")
                        .currency("COP")
                        .build()))
                .totalCompanies(TotalCompaniesOrderSalesDTO.builder()
                        .orders(TotalOrderSalesDTO.builder()
                                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .salesCurrent(TotalSalesCurrentDTO.builder()
                                        .build())
                                .salesAWeekBefore(TotalSalesAWeekBeforeDTO.builder()
                                        .build())
                                .build())
                        .currency("COP")
                        .name("name")
                        .build())
                .build();
    }
}
