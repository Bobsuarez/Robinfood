package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardCreatePipeDTO {
    private CreateCreditCardRequestDTO creditCardRequestDTO;
    private CreditCard creditCard;
    private int generatedTokens;
}
