package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class TreasureEntitiesEntity {

    private final String name;

    private final List<TreasureEntityParameterStoresEntity> parameters;



}
