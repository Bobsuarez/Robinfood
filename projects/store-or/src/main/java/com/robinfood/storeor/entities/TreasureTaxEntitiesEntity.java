package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class TreasureTaxEntitiesEntity {

    private final String name;

    private final List<TreasureTaxEntityParametersEntity> parameters;
}
