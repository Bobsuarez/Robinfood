package com.robinfood.datamock;

import com.robinfood.dtos.SubscriberDTO;

import java.util.List;

public class SubscriberDTOMock {

    public static SubscriberDTO getDefault() {

        return SubscriberDTO.builder()
                .description("Canal interesado en los cambio de estado de la orden")
                .id(1L)
                .name("POS")
                .properties(List.of(PropertyDTOMock.getDefault()))
                .type(TypeDTOMock.getDefault())
                .build();
    }
}
