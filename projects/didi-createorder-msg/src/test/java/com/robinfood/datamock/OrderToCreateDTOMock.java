package com.robinfood.datamock;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateBrandDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateCompanyDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDeliveryDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDeviceDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateOrderDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateOriginDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreatePaymentMethodDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateStoreDTO;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateUserDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class OrderToCreateDTOMock {

    public static OrderToCreateDTO getDataDefault() {
        return OrderToCreateDTO.builder()
                .delivery(OrderToCreateDeliveryDTO.builder()
                        .code("code")
                        .build())
                .paid(Boolean.TRUE)
                .device(OrderToCreateDeviceDTO.builder()
                        .ip("198.168.0.2")
                        .build())
                .company(OrderToCreateCompanyDTO.builder()
                        .id(1L)
                        .currency("COL")
                        .build())
                .origin(OrderToCreateOriginDTO.builder()
                        .id(1L)
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
                                .couponCriteriaInfo(Collections.emptyMap())
                                .build()))
                .paymentMethods(List.of(
                        OrderToCreatePaymentMethodDTO.builder()
                                .id(4L)
                                .build()))
                .user(OrderToCreateUserDTO.builder()
                        .id(1L)
                        .build()
                )
                .uuid(UUID.fromString("108645fa-b201-4bfd-8cf5-3e359aa2f84a"))
                .build();
    }

}
