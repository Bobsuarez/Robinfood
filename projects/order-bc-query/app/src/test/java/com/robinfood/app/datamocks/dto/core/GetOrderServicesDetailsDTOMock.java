package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.response.orderhistory.GetOrderServicesDetailsDTO;

import java.util.List;

public class GetOrderServicesDetailsDTOMock {

    public static List<GetOrderServicesDetailsDTO> getDefaultList(){

        return List.of(GetOrderServicesDetailsDTO.builder()
                        .id(1L)
                        .name("Domicilio MU")
                        .quantity(1)
                        .unitPrice(1000.0)
                .build());
    }
}
