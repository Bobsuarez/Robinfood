package com.robinfood.paymentmethodsbc.dto.api.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusUpdateRequestDTO {
    private String notification;

    private String identificator;

    private String key;

    private String type;
}
