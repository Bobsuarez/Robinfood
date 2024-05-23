package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TreasureDepartmentTaxesDTO {

    private List<TreasureTaxEntitiesDTO> entities;

    private Long id;

    private Long taxTypeId;
}
