package com.robinfood.localserver.electronicbillresponse.mocks.dtos;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;

public class RequestElectronicBillingDTOMock {

    public RequestElectronicBillingDTO requestElectronicBillingDTO = RequestElectronicBillingDTO.builder()
            .orderId(1L)
            .storeId(1L)
            .requestPayload("PRUEBA")
            .responsePayload("PRUEBA")
            .statusCode("1")
            .build();

}