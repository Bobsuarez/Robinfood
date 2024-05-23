package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderDetailDTO {

    private final Double consumptionValue;

    private final Boolean hasConsumption;

    private final Long id;

    private final String invoice;

    private final String notes;

}
