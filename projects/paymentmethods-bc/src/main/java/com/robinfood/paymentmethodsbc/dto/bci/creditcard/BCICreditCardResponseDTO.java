package com.robinfood.paymentmethodsbc.dto.bci.creditcard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BCICreditCardResponseDTO {
    private CreditCardDetailResult creditCard;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreditCardDetailResult {
        private boolean succeeded;
        private String status;
        private String token;
        private String type;
        private String country;
    }
}
