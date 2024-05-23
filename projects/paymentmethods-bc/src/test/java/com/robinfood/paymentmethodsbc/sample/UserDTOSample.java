package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.UserDTO;

public final class UserDTOSample {

    private UserDTOSample() {}

    public static UserDTO getUserDTO() {
        return UserDTO.builder()
            .userId(1068522L)
            .firstName("First Name")
            .lastName("Last Name")
            .phoneCode("57")
            .phoneNumber("3327775544")
            .build();
    }
}
