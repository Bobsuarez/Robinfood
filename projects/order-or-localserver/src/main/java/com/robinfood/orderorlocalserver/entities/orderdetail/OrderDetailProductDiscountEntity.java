package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Data;

@Data
public class OrderDetailProductDiscountEntity {

    private final Long id;

    private final Long typeId;

    private final Double value;
}
