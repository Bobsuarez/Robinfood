package com.robinfood.datamock;

import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;

public class ThirdPartyDTOMock {

    public static ThirdPartyDTO getDefault() {

        return ThirdPartyDTO.builder()
                .fullName("full_name")
                .email("correo@gmail.com")
                .documentType(1)
                .documentNumber("Cedula")
                .phone("123456789")
                .build();
    }
}
