package com.robinfood.core.mappers;

import com.robinfood.core.dtos.reportsalesstore.ReportTotalOrdersPaymentDTO;
import com.robinfood.core.dtos.salesstore.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.salesstore.ValueSalesOrderDTO;
import com.robinfood.core.utilities.CalculatorPercentageUtil;

import java.math.BigDecimal;

public final class ReportTotalOrdersPaymentMappers {

    private ReportTotalOrdersPaymentMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ReportTotalOrdersPaymentDTO variousSalesTotalsDTOToReportTotalOrdersPaymentDTO (
            Integer counterSalesAWeekBefore,
            Integer counterSalesCurrent,
            BigDecimal  totalSalesAWeekBefore,
            BigDecimal totalSalesCurrent
    ) {
        return ReportTotalOrdersPaymentDTO.builder()
                .name("Total")
                .salesAWeekBefore(ValueSalesOrderDTO.builder()
                        .value(totalSalesAWeekBefore)
                        .counter(counterSalesAWeekBefore)
                        .build())
                .salesCurrent(ValueSalesOrderDTO.builder()
                        .value(totalSalesCurrent)
                        .counter(counterSalesCurrent)
                        .build())
                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                        .value(CalculatorPercentageUtil.getPercentageDifference(
                                totalSalesCurrent,
                                totalSalesAWeekBefore
                        ))
                        .build())
                .build();
    }
}
