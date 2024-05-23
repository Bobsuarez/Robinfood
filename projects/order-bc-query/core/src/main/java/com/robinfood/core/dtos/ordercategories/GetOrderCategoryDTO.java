package com.robinfood.core.dtos.ordercategories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetOrderCategoryDTO {

    private Integer compensation;

    private Integer discounts;

    private Integer grossValue;

    private Integer id;

    private String name;

    private Integer netValue;

    private Integer taxes;

}
