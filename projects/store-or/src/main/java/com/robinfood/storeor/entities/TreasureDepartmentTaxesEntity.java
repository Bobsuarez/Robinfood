package com.robinfood.storeor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class TreasureDepartmentTaxesEntity {

    private final Long id;

    private final Long taxTypeId;

    private final List<TreasureTaxEntitiesEntity> entities;
}
