package com.robinfood.core.entities.menu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuGroupPortionEntity {

    private final BigDecimal discount;
    private final Long id;
    private final String image;
    private final Boolean isDefault;
    private final String name;
    private final Long parentId;
    private final BigDecimal premiumPrice;
    private final BigDecimal price;
    private final Integer position;
    private final String sku;
    private final Long unit;
    private final Double weight;
}
