package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDetailEntity {

    private String accountType;

    private  String additionalInformation;

    private String approvalCode;

    private  String franchiseType;

    private Long orderId;

    private Long orderPaymentId;

    private String posTerminalCode;

    private Long providerId;

    private String selfManagementCode;

    private Long uniqueTransactionId;

}
