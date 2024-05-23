package com.robinfood.core.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderPaymentMethodsDTO {

    private final Long id;

    private final String name;

    private final String shortName;

    private final Long typeId;

    private final Long transactions;

    private final Double value;

}
