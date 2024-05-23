package com.robinfood.app.mocks.statusorderhistory;

import com.robinfood.core.dtos.user.UserDTO;

public final class UserDTOMock {

    public static UserDTO getDataDefault() {

        return UserDTO.builder()
                .email("user@email.com")
                .firstName("Carlos")
                .id(1L)
                .isEmployee(Boolean.TRUE)
                .lastName("Torres")
                .phoneCode("+57")
                .phoneNumber("3001234567")
                .build();
    }
}
