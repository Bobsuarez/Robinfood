package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDeletePipeDTO {
    private Long userId;
    private Long creditCardId;
    private CreditCard creditCard;
    private SettingsDTO settings;
    private List<PaymentGatewayCreditCard> paymentGatewayCreditCardsToDelete;
}
