package com.robinfood.paymentmethodsbc.dto.jms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JmsUpdateTransactionStatusDTO implements Serializable {
    private static final long serialVersionUID = -8473474287491044480L;
    private String type;
    private String key;
    private String identificator;
    private String transactionCode;
    private String transactionReference;
    private String authorizationCode;
    private String message;
    private String errorCode;
    private String errorDescription;
    private Long transactionStatus;
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
