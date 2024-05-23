package com.robinfood.core.entities.menu;

import lombok.Data;

@Data
public class MenuSuggestedPortionDataEntity {

    private final Long dicUnitId;
    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final Double quantity;
    private final String sku;
}
