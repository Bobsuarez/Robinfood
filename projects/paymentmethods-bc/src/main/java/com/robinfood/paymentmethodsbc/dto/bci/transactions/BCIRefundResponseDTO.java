package com.robinfood.paymentmethodsbc.dto.bci.transactions;

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
public class BCIRefundResponseDTO {
    private boolean succeeded;

    private String type;

    private GatewayRefundResponse gatewayResponse;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GatewayRefundResponse {
        private String type;

        private String transactionCode;

        private String transactionReference;

        private String authorizationCode;
    }
}
