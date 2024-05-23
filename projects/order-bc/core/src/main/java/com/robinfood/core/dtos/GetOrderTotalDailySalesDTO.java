package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderTotalDailySalesDTO {

    private final double totalDailySales;
    private final long paymentMethodId;
    private final String paymentMethodName;
    private final long totalOrders;

}
