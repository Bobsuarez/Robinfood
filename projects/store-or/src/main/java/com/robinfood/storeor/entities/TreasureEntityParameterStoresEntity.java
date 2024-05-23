package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasureEntityParameterStoresEntity {

    private final String name;

    private final String value;
}
