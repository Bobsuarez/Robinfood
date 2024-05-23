package com.robinfood.core.mappers;

import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CurrencyDTO;
import com.robinfood.core.dtos.reportordersalescompanies.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesAWeekBeforeDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesCurrentDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalCompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalSalesAWeekBeforeDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalSalesCurrentDTO;
import com.robinfood.core.utilities.CalculatorPercentageUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public final class BuildCompaniesOrderSalesMappers {

    private BuildCompaniesOrderSalesMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static SalesAWeekBeforeDTO buildSalesAWeekBeforeDTO (
            OrderSalesByCompanyDTO orderSalesCompany,
            BigDecimal previousDateTotal
    ){

        return SalesAWeekBeforeDTO.builder()
                .currency(CurrencyDTO.builder()
                        .usd(orderSalesCompany.getOrders().getSalesAWeekBefore().getValue()
                                .divide(previousDateTotal,NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                        .build())
                .value(orderSalesCompany.getOrders().getSalesAWeekBefore().getValue())
                .build();
    }

    public static SalesCurrentDTO buildSalesCurrentDTO (
            OrderSalesByCompanyDTO orderSalesCompany,
            BigDecimal currentDateTotal
    ){

        return SalesCurrentDTO.builder()
                .currency(CurrencyDTO.builder()
                        .usd(orderSalesCompany.getOrders().getSalesCurrent().getValue()
                                .divide(currentDateTotal,NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                        .build())
                .value(orderSalesCompany.getOrders().getSalesCurrent().getValue())
                .build();
    }

    public static TotalOrderSalesDTO buildOrderSalesDTO (
            BigDecimal newSalesAWeekBeforeUsd,
            BigDecimal newSalesCurrentUsd
    ) {

        return TotalOrderSalesDTO.builder()
                .salesAWeekBefore(TotalSalesAWeekBeforeDTO.builder()
                        .currency(CurrencyDTO.builder()
                                .usd(newSalesAWeekBeforeUsd)
                                .build())
                        .build())
                .salesCurrent(TotalSalesCurrentDTO.builder()
                        .currency(CurrencyDTO.builder()
                                .usd(newSalesCurrentUsd)
                                .build())
                        .build())
                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                        .value(CalculatorPercentageUtil
                                .getPercentageDifference(
                                        newSalesCurrentUsd,
                                        newSalesAWeekBeforeUsd))
                        .build())
                .build();
    }

    public static TotalCompaniesOrderSalesDTO addValueTotalCompaniesOrderSales (
            TotalCompaniesOrderSalesDTO totalCompaniesOrderSales,
            BigDecimal newSalesAWeekBeforeUsd,
            BigDecimal newSalesCurrentUsd
    ) {

        totalCompaniesOrderSales.setOrders(buildOrderSalesDTO(
                        newSalesAWeekBeforeUsd,
                        newSalesCurrentUsd
                ));

        return totalCompaniesOrderSales;
    }
}
