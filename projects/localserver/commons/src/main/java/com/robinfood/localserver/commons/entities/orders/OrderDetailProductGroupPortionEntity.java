package com.robinfood.localserver.commons.entities.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupPortionEntity {

    private Boolean addition;

    private OrderDetailChangedPortionEntity changedPortion;

    private BigDecimal discount;

    private Integer quantityFree;

    private Long id;

    private String name;

    private Double price;

    private Long parentId;

    private Integer quantity;

    private String sku;

    private Long units;

    private Double weight;
}
