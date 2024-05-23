package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderThirdPartyDTO;

public class GetOrderThirdPartyDTOMock {

    public static OrderThirdPartyDTO getDataDefault(){

        return OrderThirdPartyDTO.builder()
                .documentNumber("22675323")
                .documentType(1L)
                .email("pedro@gmail.com")
                .fullName("Pedro Jose")
                .phone("3113673398").build();

    }
}
