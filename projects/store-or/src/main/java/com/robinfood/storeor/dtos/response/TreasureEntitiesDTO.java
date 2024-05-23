package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TreasureEntitiesDTO {

    private String name;

    private List<TreasureEntityParameterStoresDTO> parameters;
}
