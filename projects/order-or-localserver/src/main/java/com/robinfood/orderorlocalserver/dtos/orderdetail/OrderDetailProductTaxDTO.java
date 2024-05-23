package com.robinfood.orderorlocalserver.dtos.orderdetail;

import lombok.Data;

@Data
public class OrderDetailProductTaxDTO {

    private final Long familyTypeId;

    private final Long id;

    private final Double price;

    private final Long taxTypeId;

    private final String taxTypeName;

    private final Double value;
}
