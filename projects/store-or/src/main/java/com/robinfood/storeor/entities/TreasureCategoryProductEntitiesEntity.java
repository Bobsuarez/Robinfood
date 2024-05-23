package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class TreasureCategoryProductEntitiesEntity {

    private final List<CategoryTaxesEntity> categoryTaxes;

    private final String name;

    private final List<TreasureCategoryProductEntityParametersEntity> parameters;

}
