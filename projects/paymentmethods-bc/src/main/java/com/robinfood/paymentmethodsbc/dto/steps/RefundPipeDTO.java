package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundPipeDTO {
    private Long originalTransactionId;

    private Transaction originalTransaction;

    private TransactionDetail originalTransactionDetail;

    private String refundReason;

    private SettingsDTO settings;

    private boolean bciProcessRefund;

    public RefundPipeDTO(Long originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }
}
