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
public class OrderPaymentDTO {

    private OrderPaymentDetailDTO detail;

    private Double discount;

    private Long id;

    private Integer orderId;

    private Short originId;

    private Short paymentMethodId;

    private Double subtotal;

    private Double tax;

    private Double value;
}
