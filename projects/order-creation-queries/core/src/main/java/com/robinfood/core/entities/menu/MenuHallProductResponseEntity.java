package com.robinfood.core.entities.menu;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuHallProductResponseEntity {

    private final Long articleId;
    private final Long brandId;
    private final String description;
    private final BigDecimal discount;
    private final Long displayType;
    private final List<MenuProductGroupEntity> groups;
    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final Integer position;
    private final BigDecimal price;
    private final String productFlow;
    private final Long sizeId;
    private final String sku;
    private final List<String> tags;
    private final Long type;
    private final String typeName;
}
