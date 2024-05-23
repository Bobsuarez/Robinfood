package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetailServiceEntity {

    private final Double discount;

    private final Long id;

    private final String name;

    private final Integer quantity;

    private final Double subTotal;

    private final Double tax;

    private final Double taxPercentage;

    private final Double unitPrice;
}
