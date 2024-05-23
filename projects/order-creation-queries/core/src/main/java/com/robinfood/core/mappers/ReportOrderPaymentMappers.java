package com.robinfood.core.mappers;

import com.robinfood.core.dtos.reportsalesstore.ReportOrdersPaymentDTO;
import com.robinfood.core.dtos.salesstore.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.salesstore.SalesPaymentMethodDTO;
import com.robinfood.core.utilities.CalculatorPercentageUtil;

public final class ReportOrderPaymentMappers {

    private ReportOrderPaymentMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ReportOrdersPaymentDTO salesPaymentDTOToReportOrdesPaymentDTO (
            SalesPaymentMethodDTO salesPaymentMethodDTO
    ) {

        return ReportOrdersPaymentDTO.builder()
                .salesCurrent(salesPaymentMethodDTO.getOrders().getSalesCurrent())
                .salesAWeekBefore(salesPaymentMethodDTO.getOrders().getSalesAWeekBefore())
                .percentageSalesDifference(PercentageSalesDifferenceDTO.builder()
                        .value(CalculatorPercentageUtil.getPercentageDifference(
                                salesPaymentMethodDTO.getOrders().getSalesCurrent().getValue(),
                                salesPaymentMethodDTO.getOrders().getSalesAWeekBefore().getValue()
                        ))
                        .build())
                .build();
    }
}
