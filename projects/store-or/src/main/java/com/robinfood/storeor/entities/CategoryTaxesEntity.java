package com.robinfood.storeor.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryTaxesEntity {

    private final Long id;

    private final String name;

    private final List<TreasureCategoryTaxesEntityParametersEntity> parameters;

    private final Long taxTypeId;

}
