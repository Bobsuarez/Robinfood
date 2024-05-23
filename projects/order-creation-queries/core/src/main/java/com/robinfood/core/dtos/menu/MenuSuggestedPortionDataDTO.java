package com.robinfood.core.dtos.menu;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuSuggestedPortionDataDTO {

    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final String sku;
    private final Long unitId;
    private final Double unitNumber;
}
