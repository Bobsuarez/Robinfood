package com.robinfood.paymentmethodsbc.dto.steps;

import java.util.List;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardUpdatePipeDTO {
    private Long userId;
    private String token;
    private Long creditCardId;
    private UpdateCreditCardRequestDTO updateCardRequestDTO;
    private CreditCard creditCard;
    private CreditCard creditCardNew;
    private List<PaymentGatewayCreditCard> paymentGatewayCreditCardsToDelete;
    private Long paymentGatewayId;
}
