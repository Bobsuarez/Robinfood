package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDetailDTO;

public class OrderPaymentDetailDTOMock {

    public static OrderPaymentDetailDTO getDataDefault() {

        return OrderPaymentDetailDTO.builder()
                .accountType("Credit")
                .additionalInformation("Some additional info")
                .approvalCode("APPROVED123")
                .franchiseType("VISA")
                .id(1001L)
                .orderId(12345)
                .orderPaymentId((short) 1)
                .posTerminalCode("POS123")
                .providerId(2001L)
                .selfManagementCode("SELF123")
                .uniqueTransactionId(3001L)
                .build();
    }
}
