package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderAddressDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOriginDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseStoreDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseOrderDetailMock {

    public static ResponseOrderDetailDTO getResponseOrderDetail() {
        return ResponseOrderDetailDTO.builder()
                .id(1L)
                .uid("12345abcde")
                .flowId(1L)
                .paid(true)
                .orderNumber("123")
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
                .address(
                        ResponseOrderAddressDTO.builder()
                                .address("CL 1 No 90 - 40")
                                .notes("Edificio Donde Vivo , Apto 009")
                                .latitude("4.666378")
                                .longitude("-74.057391")
                                .zipCode("4324435")
                                .build()
                )
                .build();
    }
}
