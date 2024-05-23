package com.robinfood.core.dtos.ordercategories;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class OrderCategoryDTO {

    public Double compensation;

    public Double discounts;

    public Double grossValue;

    public Integer id;

    public String name;

    public Double netValue;

    public Double taxes;

}
