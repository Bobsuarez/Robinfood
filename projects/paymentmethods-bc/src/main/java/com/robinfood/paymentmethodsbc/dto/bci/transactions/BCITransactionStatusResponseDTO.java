package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BCITransactionStatusResponseDTO implements Serializable{
    private static final long serialVersionUID = 8107840391310535089L;
    private TransactionStatusResponse transaction;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TransactionStatusResponse implements Serializable {
        private static final long serialVersionUID = -4360908992201313852L;
        private boolean success;
        private Long transactionStatus;
        private String accepted;
        private String reason;
        private String event;
        private String reference;
        private String date;
        private String transactionCode;
        private String transactionReference;
        private String authorizationCode;
        private String uuid;
        private String paymentGatewayStatus;
        private String paymentGatewayLevelCategory;
        private String dataphoneCode;
        private String creditCardMaskedNumber;
        private String accountType;
        private String franchise;
        private String dataphoneReceiptNumber;
        private Integer installments;
        private String establishmentCode;
        private BigDecimal transactionValue;
        private BigDecimal transactionTaxValue;           
    }
}
