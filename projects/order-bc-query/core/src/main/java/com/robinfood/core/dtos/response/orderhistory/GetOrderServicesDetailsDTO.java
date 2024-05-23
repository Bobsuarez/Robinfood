package com.robinfood.core.dtos.response.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GetOrderServicesDetailsDTO {

    private final Double discount;

    private final Long id;

    private final String name;

    private final Integer quantity;

    private final Double subTotal;

    private final Double tax;

    private final Double taxPercentage;

    private final Double unitPrice;
}
