package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class OrderDiscountDTO {

    private Long discountId;

    private Double discountValue;

    private Long id;

    private Long orderDiscountTypeId;

    private Long orderId;

    private Long orderFinalProductId;
}
