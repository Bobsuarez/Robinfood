package com.robinfood.core.entities.co2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class CO2PortionEntity {
    private Long id;

    private String sku;

    private Integer quantity;
}
