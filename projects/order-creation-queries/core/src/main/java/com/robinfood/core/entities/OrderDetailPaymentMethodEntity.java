package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDetailPaymentMethodEntity {

    private final Double discount;

    private final Long id;

    private final Long originId;

    private final Double subtotal;

    private final Double tax;

    private final Double value;
}
