package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ReplacementPortionDTO {

    private Long id;

    private String name;

    private PortionProductDTO product;

    private String sku;

    private Long unitId;

    private Double unitNumber;

}
