package com.robinfood.core.dtos.menu;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuStoreBrandDTO {

    private final Long id;

    private final Long storeId;

    private final String storeName;
}
