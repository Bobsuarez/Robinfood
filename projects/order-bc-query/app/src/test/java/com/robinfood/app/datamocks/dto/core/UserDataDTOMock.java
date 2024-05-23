package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.UserDataDTO;

public class UserDataDTOMock {

    public static UserDataDTO getDataDefault() {

        return UserDataDTO.builder()
                .email("muy@robinfood.com")
                .firstName("Alejo")
                .id(1L)
                .lastName("Martinez")
                .mobile("34343434")
                .orderId(1L)
                .build();
    }

}
