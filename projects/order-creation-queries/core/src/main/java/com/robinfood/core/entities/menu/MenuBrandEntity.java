package com.robinfood.core.entities.menu;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuBrandEntity {

    private final String color;

    private final Long countryId;

    private final Long franchiseId;

    private final Long id;

    private final String image;

    private final String name;

    private final MenuStoreBrandEntity storeBrand;
}
