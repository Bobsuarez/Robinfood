package com.robinfood.paymentmethodsbc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaymentReportDTO implements Serializable {
    private static final long serialVersionUID = 2039193851495177276L;
    private String eventLog;
    private String subEventLog;
    private String status;
    private String uuid;
    private String country;
    private long countryId;
    private long storeId;
    private String referencePaymentMethods;
    private String userPhone;
    private long userId;
    private BigDecimal total;
    private long generalPaymentMethodId;
    private String generalPaymentMethodName;
    private long platformId;
    private String platformName;
    private long paymentGatewayId;
    private long transactionId;
    private String transactionDataphoneTerminalCode;
    private String transactionDataphoneUniqueCode;
    private String transactionTerminalUuid;
    private String transactionTerminalName;
    private String creditCardMaskedNumber;
    private String accountType;
    private String franchise;
    private String dataphoneReceiptNumber;
    private Integer installments;
    private String establishmentCode;
    private String paymentBci;
    private String paymentBciResponse;
    private String statusCode;
    private String statusCategory;
    private String message;
}
