package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreMultiBrandDTO {

    private final String color;

    private final String image;

    private final String name;
}
