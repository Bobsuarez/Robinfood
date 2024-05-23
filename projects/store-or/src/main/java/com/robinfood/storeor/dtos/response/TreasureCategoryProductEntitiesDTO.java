package com.robinfood.storeor.dtos.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TreasureCategoryProductEntitiesDTO {

    private List<CategoryTaxesDTO> categoryTaxes;

    private String name;

    private final List<TreasureCategoryProductEntityParametersDTO> parameters;
}
