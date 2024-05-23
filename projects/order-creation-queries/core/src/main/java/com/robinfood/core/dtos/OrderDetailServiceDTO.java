package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailServiceDTO {

    private final Double discount;

    private final Long id;

    private final String name;

    private final Integer quantity;

    private final Double subTotal;

    private final Double tax;

    private final Double taxPercentage;

    private final Double unitPrice;
}
