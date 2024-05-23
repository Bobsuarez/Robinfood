package com.robinfood.core.entities.menu;

import lombok.Data;

import java.util.List;

@Data
public class MenuProductGroupEntity {

    private final Integer free;
    private final Long groupTypeId;
    private final Long id;
    private final Integer max;
    private final Integer min;
    private final String namePlural;
    private final String nameSingular;
    private final List<MenuGroupPortionEntity> portions;
    private final String selectionNamePlural;
    private final String selectionNameSingular;
    private final String sku;
    private final Integer subsidy;
}
