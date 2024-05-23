package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailPaymentMethodDTO {

    private final Double discount;

    private final Long id;

    private final Double subtotal;

    @JsonIgnore
    private final Long orderId;

    private final Long originId;

    private final Double tax;

    private final Double value;

}
