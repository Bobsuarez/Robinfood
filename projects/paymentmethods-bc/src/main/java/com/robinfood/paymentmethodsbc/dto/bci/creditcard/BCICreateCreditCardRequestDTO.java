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
public class BCICreateCreditCardRequestDTO {
    private CreditCardDetailsDTO creditCard;

    private BCISettingsDTO settings;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreditCardDetailsDTO {
        private String userId;

        private String firstName;

        private String lastName;

        private int identificationType;

        private String identificationNumber;

        private String email;

        private int isUpdated;

        private String number;

        private Integer verificationValue;

        private String creditCardTypeCode;

        private Integer month;

        private Integer year;
    }
}
