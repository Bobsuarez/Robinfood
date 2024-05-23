package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class ServiceDTO {

    private Double discount;

    @NotNull
    @Positive
    private Long id;

    private Double priceNt;

    private Long serviceId;

    private Double taxPercentage;

    private Double taxPrice;

    private Double total;

    @NotNull
    private Double value;

}
