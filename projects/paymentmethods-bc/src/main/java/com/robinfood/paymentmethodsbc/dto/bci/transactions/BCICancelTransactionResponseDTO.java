package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BCICancelTransactionResponseDTO {
    private String error;
    private String success;
    private String msg;
    private Integer statusCode;
}
