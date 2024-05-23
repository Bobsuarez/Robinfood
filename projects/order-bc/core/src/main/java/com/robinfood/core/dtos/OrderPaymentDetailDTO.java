package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderPaymentDetailDTO {

    private String accountType;

    private  String additionalInformation;

    private String approvalCode;

    private  String franchiseType;

    private Long id;

    private Long orderId;

    private Long orderPaymentId;

    private String posTerminalCode;

    private Long providerId;

    private String selfManagementCode;

    private Long uniqueTransactionId;
}
