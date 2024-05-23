package com.robinfood.orderorlocalserver.dtos.orderdetail;

import lombok.Data;

@Data
public class OrderDetailDiscountDTO {

    private final Long id;

    private final Long typeId;

    private final Double value;
}
