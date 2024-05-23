package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailFinalProductPortionDTO {

    private final Boolean addition;

    private DetailChangedPortionDTO changedPortion;

    private final BigDecimal discount;

    @JsonIgnore
    private final Long finalProductId;

    @JsonIgnore
    private final Long groupId;

    @JsonIgnore
    private final String groupName;

    private final Long id;

    private String name;

    @JsonIgnore
    private final Long orderId;

    private final Double price;

    private final Long productId;

    private final Integer quantity;

    @JsonProperty("free")
    private Integer quantityFree;

    private final String sku;

    private final Long units;

    private final Double weight;

}
