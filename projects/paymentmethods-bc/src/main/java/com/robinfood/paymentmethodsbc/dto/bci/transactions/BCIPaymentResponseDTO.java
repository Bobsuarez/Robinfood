package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BCIPaymentResponseDTO implements Serializable {
    private static final long serialVersionUID = -7770501932130021603L;
    private boolean succeeded;
    private boolean canceled;
    private boolean pending;
    private String type;
    private TransactionGatewayResponse gatewayResponse;
    private Long transactionStatus;
    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TransactionGatewayResponse implements Serializable {
        private static final long serialVersionUID = -8116168934482401807L;
        private String type;
        private String transactionCode;
        private String transactionReference;
        private String authorizationCode;
        private String message;
        private String errorCode;
        private String errorDescription;
        private String paymentGatewayLevelCategory;
        private String paymentGatewayStatus;
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
