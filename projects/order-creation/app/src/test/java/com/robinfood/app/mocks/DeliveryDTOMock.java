package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.DeliveryDTO;

public class DeliveryDTOMock {

    public static DeliveryDTO build() {
        return DeliveryDTO.builder()
                .addressCity("city")
                .build();
    }
}
