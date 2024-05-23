package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class OrderPaymentDetailDTO {

    private String accountType;

    private String additionalInformation;

    private String approvalCode;

    private String franchiseType;

    private Long id;

    private Integer orderId;

    private Short orderPaymentId;

    private String posTerminalCode;

    private Long providerId;

    private String selfManagementCode;

    private Long uniqueTransactionId;
}
