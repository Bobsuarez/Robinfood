package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.UserDataDTO;

public class UserDataDTOMock {

    public static UserDataDTO getDataDefault() {
        return UserDataDTO.builder()
                .email("john@site")
                .firstName("John")
                .id(1L)
                .lastName("Mason")
                .mobile("34343433")
                .orderId(1L)
                .build();
    }

}
