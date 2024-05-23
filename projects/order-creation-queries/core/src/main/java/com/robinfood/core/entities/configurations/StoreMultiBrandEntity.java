package com.robinfood.core.entities.configurations;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreMultiBrandEntity {

    private final String color;

    private final String image;

    private final String name;
}
