package com.robinfood.paymentmethodsbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreditCardResponseDTO {
    private String creditCardToken;

    private Integer statusCode;

    private String errorMessage;
}
