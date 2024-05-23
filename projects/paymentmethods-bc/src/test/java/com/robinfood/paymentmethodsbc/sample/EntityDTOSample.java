package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.EntityDTO;

public final class EntityDTOSample {

    private EntityDTOSample() {}

    public static EntityDTO getEntityDTO() {
        return EntityDTO.builder()
            .id(1L)
            .source(5587594L)
            .reference("9d668189-3320-4aaa-9012-667fca977f18")
            .build();
    }
}
