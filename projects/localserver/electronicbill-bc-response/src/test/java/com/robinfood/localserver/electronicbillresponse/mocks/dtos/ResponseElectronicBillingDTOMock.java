package com.robinfood.localserver.electronicbillresponse.mocks.dtos;

import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;

import java.time.LocalDateTime;

public class ResponseElectronicBillingDTOMock {

    public ResponseElectronicBillingDTO responseElectronicBillingDTO = ResponseElectronicBillingDTO.builder()
            .id("f73ff67f-ff68-4fbd-b058-6f2d669c67fd")
            .message("Created order electronic billing")
            .createdAt(LocalDateTime.now())
            .build();
}
