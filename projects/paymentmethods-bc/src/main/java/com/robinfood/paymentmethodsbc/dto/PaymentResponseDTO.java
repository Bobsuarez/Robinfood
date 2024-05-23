package com.robinfood.paymentmethodsbc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponseDTO implements Serializable {
    private static final long serialVersionUID = -6115621417770932608L;
    private Long transactionStatus;
    private String authorizationCode;
    private String transactionCode;
    private String transactionReference;
    private String transactionType;
    private String bciResponseBody;
    private Integer statusCode;
    private String errorCode;
    private String errorDescription;
    private String paymentGatewayStatus;
    private String paymentGatewayLevelCategory;
    private String message;
    private String uuid;
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
