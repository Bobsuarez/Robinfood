package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDailySaleSummaryDTO {

    private Double salesSummary;
    private Integer ordersNumber;
}
