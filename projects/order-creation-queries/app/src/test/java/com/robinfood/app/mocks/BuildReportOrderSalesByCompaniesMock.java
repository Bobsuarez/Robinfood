package com.robinfood.app.mocks;

import com.robinfood.core.dtos.orderSales.OrderActiveSalesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.orderSales.SalesActiveAWeekBeforeDTO;
import com.robinfood.core.dtos.orderSales.SalesActiveCurrentDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CurrencyDTO;
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
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class BuildReportOrderSalesByCompaniesMock {

    public static Optional<CompaniesOrderSalesDTO> buildCompaniesOrderSales() {

        return Optional.ofNullable(
                CompaniesOrderSalesDTO.builder()
                        .currency("COP")
                        .id(1L)
                        .name("")
                        .orders(OrderSalesDTO.builder()
                                .salesAWeekBefore(SalesAWeekBeforeDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .currency(CurrencyDTO.builder()
                                                .usd(BigDecimal.valueOf(0.0000).setScale(4, RoundingMode.DOWN))
                                                .build())
                                        .build())
                                .salesCurrent(SalesCurrentDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .currency(CurrencyDTO.builder()
                                                .usd(BigDecimal.valueOf(0.0000).setScale(4, RoundingMode.DOWN))
                                                .build())
                                        .build())
                                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                                        .value(BigDecimal.valueOf(0.0))
                                        .build())
                                .build())
                        .build()
        );
    }

    public static TotalCompaniesOrderSalesDTO buildTotalCompaniesOrderSalesFinal() {

        return TotalCompaniesOrderSalesDTO.builder()
                .name("Total de la compañia")
                .currency("USD")
                .orders(TotalOrderSalesDTO.builder()
                        .salesAWeekBefore(TotalSalesAWeekBeforeDTO.builder()
                                .currency(CurrencyDTO.builder()
                                        .usd(BigDecimal.valueOf(0.0000).setScale(4, RoundingMode.DOWN))
                                        .build())
                                .build())
                        .salesCurrent(TotalSalesCurrentDTO.builder()
                                .currency(CurrencyDTO.builder()
                                        .usd(BigDecimal.valueOf(0.0000).setScale(4, RoundingMode.DOWN))
                                        .build())
                                .build())
                        .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                                .value(BigDecimal.valueOf(0.0))
                                .build())
                        .build())
                .build();
    }

    public static TotalCompaniesOrderSalesDTO buildTotalCompaniesOrderSales() {

       return TotalCompaniesOrderSalesDTO.builder()
               .name("name")
               .currency("COP")
               .orders(TotalOrderSalesDTO.builder()
                       .salesAWeekBefore(TotalSalesAWeekBeforeDTO.builder()
                               .currency(CurrencyDTO.builder()
                                       .usd(BigDecimal.valueOf(0.0))
                                       .build())
                               .build())
                       .salesCurrent(TotalSalesCurrentDTO.builder()
                               .currency(CurrencyDTO.builder()
                                       .usd(BigDecimal.valueOf(0.0))
                                       .build())
                               .build())
                       .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                               .value(BigDecimal.valueOf(0.0))
                               .build())
                       .build())
               .build();
   }

    public static ResponseOrderSalesDTO buildResponseReport() {

        return ResponseOrderSalesDTO.builder()
                .companies(List.of(buildCompaniesOrderSales().get()))
                .totalCompanies(null)
                .build();
    }

    public static OrderSalesByCompanyDTO orderSalesByCompanyDTO() {

        return OrderSalesByCompanyDTO.builder()
                .id(1L)
                .orders(OrderActiveSalesDTO.builder()
                        .salesAWeekBefore(SalesActiveAWeekBeforeDTO.builder()
                                .value(BigDecimal.valueOf(0.0000))
                                .build())
                        .salesCurrent(SalesActiveCurrentDTO.builder()
                                .value(BigDecimal.valueOf(0.0000))
                                .build())
                        .build())
                .build();
    }

    public static ResponseOrderSalesDTO responseOrderSalesDTO() {

        return ResponseOrderSalesDTO.builder()
                .totalCompanies(buildTotalCompaniesOrderSalesFinal())
                .companies(List.of(buildCompaniesOrderSales().get()))
                .build();
    }
}
