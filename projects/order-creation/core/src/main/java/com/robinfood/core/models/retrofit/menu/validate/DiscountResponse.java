package com.robinfood.core.models.retrofit.menu.validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountResponse {

    private Long discountTypeId;

    private String discountType;

    private Long id;

    private Double maxDiscount;

    private String name;

    private Double value;

}
