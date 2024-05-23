package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailPaymentMethodDTO {

    private final Double discount;

    private final Long id;

    private final Double subtotal;

    private final String name;

    @JsonIgnore
    private final Long orderId;

    private final Long originId;

    private final Double tax;

    private final Double value;

}
