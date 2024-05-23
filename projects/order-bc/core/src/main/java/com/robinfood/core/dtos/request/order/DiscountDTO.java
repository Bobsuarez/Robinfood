package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountDTO {

    private Long id;

    @Transient
    private Boolean isConsumptionDiscount;

    private Boolean isProductDiscount;

    private Long orderFinalProductId;

    private Long orderId;

    private Long typeId;

    private Double value;

    @Transient
    @NotNull
    private String sku;
}
