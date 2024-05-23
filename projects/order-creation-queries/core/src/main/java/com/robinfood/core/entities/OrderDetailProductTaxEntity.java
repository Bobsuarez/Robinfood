package com.robinfood.core.entities;

import lombok.Data;

@Data
public class OrderDetailProductTaxEntity {

    private final Long familyTypeId;

    private final Long id;
    
    private final Double price;

    private final Long taxTypeId;

    private final String taxTypeName;

    private final Double value;
}
