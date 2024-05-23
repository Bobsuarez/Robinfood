package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderTotalDailySalesResponseDTO {

    private final double totalDailySales;
    private final long paymentMethodId;
    private final String paymentMethodName;
    private final long totalOrders;

}
