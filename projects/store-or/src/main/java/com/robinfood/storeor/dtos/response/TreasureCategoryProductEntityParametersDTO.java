package com.robinfood.storeor.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TreasureCategoryProductEntityParametersDTO {

    private String name;

    private String value;
}
