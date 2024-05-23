package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasureCategoryTaxesEntityParametersEntity {

    private final String name;

    private final String value;
}
