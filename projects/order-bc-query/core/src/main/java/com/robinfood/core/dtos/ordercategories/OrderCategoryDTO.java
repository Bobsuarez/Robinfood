package com.robinfood.core.dtos.ordercategories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderCategoryDTO {

    private Double compensation;

    private Double discounts;

    private Double grossValue;

    private Long id;

    private String name;

    private Double netValue;

    private Double taxes;

}
