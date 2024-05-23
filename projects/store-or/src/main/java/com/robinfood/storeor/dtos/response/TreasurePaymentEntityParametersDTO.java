package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasurePaymentEntityParametersDTO {

    private final String name;

    private final String value;
}
