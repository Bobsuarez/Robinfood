package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;
import com.robinfood.core.utilities.CalculatorPercentageUtil;

public final class OrdersSegmentToOrdersResponseMappers {

    private OrdersSegmentToOrdersResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrdersDTOResponse ordersResponse(OrdersSalesSegmentDTO ordersSalesSegmentDTO) {

        return OrdersDTOResponse.builder()
                .salesAWeekBefore(ValueAWeekBeforeToResponseMappers
                        .valueAssignment(ordersSalesSegmentDTO.getSalesAWeekBefore().getValue()))
                .salesCurrent(ValueCurrentToResponseMappers
                        .valueAssignment(ordersSalesSegmentDTO.getSalesCurrent().getValue()))
                .percentageSalesDifference(PercentageToResponseMappers.valueAssignment(
                        CalculatorPercentageUtil.getPercentageDifference(
                                ordersSalesSegmentDTO.getSalesCurrent().getValue(),
                                ordersSalesSegmentDTO.getSalesAWeekBefore().getValue()
                        )))
                .build();
    }
}
