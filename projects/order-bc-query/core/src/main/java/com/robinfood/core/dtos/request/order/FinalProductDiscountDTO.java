package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class FinalProductDiscountDTO {

    private Boolean isProductDiscount;
    private Double value;

}
