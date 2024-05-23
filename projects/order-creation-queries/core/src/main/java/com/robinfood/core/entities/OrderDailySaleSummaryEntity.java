package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDailySaleSummaryEntity {
    private Double salesSummary;
    private Integer ordersNumber;
}
