package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderTotalDailySalesDTO {

    private final String totalDailySales;
    private final long paymentMethodId;
    private final String paymentMethodName;
    private final long totalOrders;

}
