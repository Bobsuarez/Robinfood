package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionPaymentDTO {

    private OrderPaymentDetailDTO detail;

    private Long id;

    private Long originId;

    private Double value;

}
