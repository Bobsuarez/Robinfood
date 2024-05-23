package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupPortionDTO {

    private final Boolean addition;

    private OrderDetailChangedPortionDTO changedPortion;

    private final BigDecimal discount;

    private Long id;

    private String name;

    private Long parentId;

    private final Double price;

    private final Integer quantity;

    @JsonProperty("free")
    private final Integer quantityFree;

    private String sku;

    private final Long units;

    private final Double weight;

    public boolean hasReplacement() {
        return changedPortion != null;
    }
}
