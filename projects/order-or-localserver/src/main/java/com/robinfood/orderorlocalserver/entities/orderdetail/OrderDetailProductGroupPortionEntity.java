package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailProductGroupPortionEntity {

    private final Boolean addition;

    private final OrderDetailChangedPortionEntity changedPortion;

    private final BigDecimal discount;

    private final Integer free;

    private final Long id;

    private final String name;

    private final Double price;

    private final Long productId;

    private final Integer quantity;

    private final String sku;

    private final Long units;

    private final Double weight;
}
