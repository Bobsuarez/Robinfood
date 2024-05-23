package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TreasureTaxEntityParametersDTO {

    private String name;

    private String value;
}
