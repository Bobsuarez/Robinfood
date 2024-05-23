package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailDiscountDTO {

    private final Long id;

    @JsonIgnore
    private final Long orderId;

    @JsonIgnore
    private final Long productId;

    private final Long typeId;

    private final Double value;
}
