package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class DeliveryTypeDTO {

    private final String description;

    private final Long id;

    private final Boolean isIntegration;

    private final Boolean isInternalDelivery;

    private final Boolean isOnPremise;

    private final String name;
}
