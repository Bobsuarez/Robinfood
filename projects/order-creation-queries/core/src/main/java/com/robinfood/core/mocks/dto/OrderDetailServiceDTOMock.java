package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.OrderDetailServiceDTO;

import java.util.List;

public class OrderDetailServiceDTOMock {

    public static final List<OrderDetailServiceDTO> getDefaultList(){

        return List.of(OrderDetailServiceDTO.builder()
                        .id(1L)
                        .name("Domicilio MU")
                        .quantity(1)
                        .unitPrice(1000.0)
                .build());
    }
}
