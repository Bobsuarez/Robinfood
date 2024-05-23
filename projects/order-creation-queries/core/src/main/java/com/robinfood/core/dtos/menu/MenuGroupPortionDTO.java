package com.robinfood.core.dtos.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuGroupPortionDTO {

    private final Boolean isDefault;
    private final BigDecimal discount;
    private final Long id;
    private final String image;
    private final String name;
    private final Long parentId;
    private final BigDecimal premiumPrice;
    private final BigDecimal price;
    private final Integer position;
    private final String sku;
    private final Long unit;
    private final Double weight;
}
