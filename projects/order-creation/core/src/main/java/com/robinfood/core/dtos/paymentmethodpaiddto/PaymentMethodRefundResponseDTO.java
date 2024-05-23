package com.robinfood.core.dtos.paymentmethodpaiddto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Data
public class PaymentMethodRefundResponseDTO {

    private Integer code;

    private String message;
}