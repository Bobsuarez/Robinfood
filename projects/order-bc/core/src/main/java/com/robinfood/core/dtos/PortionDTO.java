package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class PortionDTO {

    @Nullable
    private Long companyId;

    private Double discount;

    private Long id;

    private Boolean free;

    private PortionGroupDTO group;

    private String name;

    @Nullable
    private Long orderFinalProductId;

    @Nullable
    private Long orderId;

    @Nullable
    private Long orderFinalProductPortionId;

    private Double price;

    private PortionProductDTO product;

    private ReplacementPortionDTO replacementPortion;

    private Integer quantity;

    @Nullable
    private Long storeId;

    private Long unitId;

    private Long unitNumber;
}
