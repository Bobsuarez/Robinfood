package com.robinfood.core.dtos.request.order;

import com.robinfood.core.dtos.PortionProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OriginalReplacementPortionDTO {

    private Long id;

    private String name;

    private PortionProductDTO product;

    private String sku;

    private Long unitId;

    private Double unitNumber;
}
