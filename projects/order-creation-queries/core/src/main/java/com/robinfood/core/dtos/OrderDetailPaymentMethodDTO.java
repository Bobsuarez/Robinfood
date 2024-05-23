package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailPaymentMethodDTO {

    private final Double discount;

    private final Long id;

    private final Long originId;

    private final Double subtotal;

    private final Double tax;

    private final Double value;

}
