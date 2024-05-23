package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDetailChangedPortionEntity {

    private final Long id;

    private final String name;

    private final Long productId;

    private final String sku;

    private final Long unitId;

    private final Double unitNumber;
}
