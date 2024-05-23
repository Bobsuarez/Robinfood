package com.robinfood.core.models.retrofit.menu.validate;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductResponse {

    private List<DiscountResponse> discounts;

    private String entity;

    private Integer quantity;

    private Long sourceId;

    private Double value;

}
