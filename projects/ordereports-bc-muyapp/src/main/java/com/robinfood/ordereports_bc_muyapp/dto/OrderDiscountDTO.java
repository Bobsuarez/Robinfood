package com.robinfood.ordereports_bc_muyapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
public class OrderDiscountDTO {

    private Long discountId;

    private Double discountValue;

    private Long id;

    private Long orderDiscountTypeId;

    private Integer orderId;

    private Long orderFinalProductId;
}
