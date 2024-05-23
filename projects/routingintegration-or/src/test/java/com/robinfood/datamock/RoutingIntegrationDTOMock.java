package com.robinfood.datamock;

import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;

public class RoutingIntegrationDTOMock {

    public static RoutingIntegrationDTO getDefault() {

        return RoutingIntegrationDTO.builder()
                .channelId(1L)
                .description("Se entrega la orden al domicilario")
                .flow(FlowDTOMock.getDefault())
                .id(2L)
                .name("POS")
                .url("https://lambdachangeorderstatus-bc.muydev.com/api/vi/orders/states/pos/changestate")
                .build();
    }
}
