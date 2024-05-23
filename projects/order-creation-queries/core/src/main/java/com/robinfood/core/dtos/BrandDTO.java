package com.robinfood.core.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BrandDTO {

    private final String color;

    private final String image;

    private final String name;
}
