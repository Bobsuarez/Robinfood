package com.robinfood.paymentmethodsbc.dto.bci.creditcard;

import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BCIDeleteCreditCardRequestDTO {
    private CreditCardDetailsDTO creditCard;

    private BCISettingsDTO settings;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreditCardDetailsDTO {
        private String token;

        private String userId;
    }
}
