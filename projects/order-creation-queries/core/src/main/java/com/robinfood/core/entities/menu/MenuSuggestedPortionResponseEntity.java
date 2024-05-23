package com.robinfood.core.entities.menu;

import lombok.Data;

import java.util.List;

@Data
public class MenuSuggestedPortionResponseEntity {

    private final List<MenuSuggestedPortionDataEntity> changes;
    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final String sku;
}
