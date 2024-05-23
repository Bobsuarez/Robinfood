package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailProductGroupEntity {

    private final Long id;

    private final String name;

    private final List<OrderDetailProductGroupPortionEntity> portions;

    private final List<OrderDetailRemovedPortionEntity> removedPortions;

    private final String sku;
}
