package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PortionDTO {

    private BigDecimal discount;

    private Integer free;

    private Long id;

    private Boolean isIncluded;

    private String name;

    private BigDecimal price;

    private PortionProductDTO product;

    private Integer quantity;

    private ReplacementPortionDTO replacementPortion;

    private String sku;

    private Long unitId;

    private Long unitNumber;
}
