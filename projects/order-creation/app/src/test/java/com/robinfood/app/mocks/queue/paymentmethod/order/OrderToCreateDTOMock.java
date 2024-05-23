package com.robinfood.app.mocks.queue.paymentmethod.order;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateBrandDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateCompanyDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateOrderDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateOriginDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateStoreDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateThirdPartyDTO;

import java.util.List;

public final class OrderToCreateDTOMock {

    public static OrderToCreateDTO getDataDefault() {
        return OrderToCreateDTO.builder()
                .paid(Boolean.TRUE)
                .company(OrderToCreateCompanyDTO.builder()
                        .id(1L)
                        .currency("COL")
                        .build())
                .orders(List.of(
                        OrderToCreateOrderDTO.builder()
                                .origin(OrderToCreateOriginDTO.builder()
                                        .id(10L)
                                        .name("origin")
                                        .build())
                                .store(OrderToCreateStoreDTO.builder()
                                        .name("store")
                                        .build())
                                .brand(OrderToCreateBrandDTO.builder()
                                        .name("brand")
                                        .build())
                                .thirdParty(
                                        OrderToCreateThirdPartyDTO.builder()
                                                .documentNumber("123456")
                                                .documentType(1l)
                                                .email("test@muy.com")
                                                .fullName("customer test")
                                                .phone("300123456789")
                                                .build()

                                )
                                .build()
                ))
                .build();
    }

}
