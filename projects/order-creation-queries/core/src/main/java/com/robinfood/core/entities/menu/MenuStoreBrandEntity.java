package com.robinfood.core.entities.menu;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuStoreBrandEntity {

    private final Long id;

    private final Long storeId;

    private final String storeName;
}
