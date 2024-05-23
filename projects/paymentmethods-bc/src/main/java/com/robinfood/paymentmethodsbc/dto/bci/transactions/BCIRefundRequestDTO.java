package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BCIRefundRequestDTO {
    private BCIRefundTransaction transaction;

    private BCISettingsDTO settings;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BCIRefundTransaction {
        private String transactionCode;

        private String transactionReference;

        private String reason;
    }
}
