package com.robinfood.storeor.dtos.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TreasureTaxEntitiesDTO {

    private String name;

    private List<TreasureTaxEntityParametersDTO> parameters;
}
