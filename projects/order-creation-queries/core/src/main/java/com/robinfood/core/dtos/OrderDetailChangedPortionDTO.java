package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderDetailChangedPortionDTO {

    private final Long id;

    private final String name;

    private final Long parentId;

    private final String sku;

    private final Long unitId;

    private final Double unitNumber;
}
