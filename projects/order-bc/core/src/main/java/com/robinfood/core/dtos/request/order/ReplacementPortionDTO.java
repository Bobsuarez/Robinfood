package com.robinfood.core.dtos.request.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robinfood.core.dtos.PortionProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class ReplacementPortionDTO {

    private final Long id;

    private final String name;

    private final PortionProductDTO product;

    private final String sku;

    private final Long unitId;

    private final Double unitNumber;

    @JsonIgnore
    private OriginalReplacementPortionDTO originalReplacementPortionDTO;
}
