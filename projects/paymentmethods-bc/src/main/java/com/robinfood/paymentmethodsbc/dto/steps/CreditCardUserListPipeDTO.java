package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardUserListPipeDTO {
    private Long userId;

    private Long countryId;

    private Long generalPaymentMethodId;

    private PaymentGateway paymentGateway;

    private List<CreditCard> creditCardList;
}
