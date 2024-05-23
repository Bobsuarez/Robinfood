package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOriginDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseStoreDTO;

public class ResponseOrderDetailMock {

    public static ResponseOrderDetailDTO getResponseOrderDetail() {
        return ResponseOrderDetailDTO.builder()
            .id(1L)
            .uid("12345abcde")
            .flowId(1L)
            .paid(true)
            .orderNumber("123")
            .paymentModelId(1L)
            .origin(
                ResponseOriginDTO.builder()
                    .platformId(1L)
                    .store(
                        ResponseStoreDTO.builder()
                            .id(1L)
                            .name("RFP Bosa")
                            .build()
                    )
                    .build()
            )
            .build();
    }
}
