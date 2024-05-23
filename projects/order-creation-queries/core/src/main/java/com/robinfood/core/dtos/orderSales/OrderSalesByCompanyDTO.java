package com.robinfood.core.dtos.orderSales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderSalesByCompanyDTO {

    private Long id;

    private OrderActiveSalesDTO orders;
}
