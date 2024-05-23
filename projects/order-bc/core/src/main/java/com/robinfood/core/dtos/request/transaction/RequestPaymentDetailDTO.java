package com.robinfood.core.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestPaymentDetailDTO {

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
