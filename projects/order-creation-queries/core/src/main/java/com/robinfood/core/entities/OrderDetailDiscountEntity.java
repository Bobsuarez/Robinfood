package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDetailDiscountEntity {

    private final Long id;

    private final Long typeId;

    private final Double value;
}
