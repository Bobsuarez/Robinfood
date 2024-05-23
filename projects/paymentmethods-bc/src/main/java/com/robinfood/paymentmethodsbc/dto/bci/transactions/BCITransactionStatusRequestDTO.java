package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BCITransactionStatusRequestDTO {
    private String notification;

    private BCISettingsDTO settings;

    private TransactionDetailsDTO transaction;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionDetailsDTO {
        private Long id;
        private String uuid;
        private String reference;
        private Long sourceId;
        private BigDecimal total;
        private BigDecimal tax;
        private BigDecimal subtotal;
    }
}
