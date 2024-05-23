package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuProductDTO {

    private final Long brandId;

    private final String description;

    private final BigDecimal discount;

    private final Long displayType;

    private final Long id;

    private final String image;

    private final String name;

    private final Long parentId;

    private final Long position;

    private final BigDecimal price;

    private final String productFlow;

    private final Long sizeId;

    private final String sku;

    private final Long typeId;
}
