package com.robinfood.dtos.sendordertosimba.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountDTO {

    private Long id;

    private Boolean isConsumptionDiscount;

    private Boolean isProductDiscount = Boolean.FALSE;

    private Long orderFinalProductId;

    private Long productId;

    private Long typeId;

    private String SKU = "";

    private BigDecimal value;
}
