package com.robinfood.ordereports.mocks;

import com.robinfood.ordereports.dtos.orders.CourierDTO;
import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.dtos.orders.ProviderDTO;
import com.robinfood.ordereports.dtos.orders.StatusDTO;
import com.robinfood.ordereports.dtos.orders.StatusTraceDTO;

import java.util.Collections;
import java.util.List;

public final class DeliveryCourierServiceDTOMock {

    public static DeliveryCourierServiceDTO getDataDefault() {
        return DeliveryCourierServiceDTO.builder()
                .deliveryCourier(getCourierDTOMock())
                .id(1223454L)
                .integrationId("abc123")
                .provider(getProviderDTOMock())
                .referenceId(0L)
                .referenceUid("abc-182-aaaaa-2019")
                .referenceUuid("asasdasda-0989as-adasdas")
                .status(getStatusDTOMock())
                .synced(Boolean.TRUE)
                .build();
    }

    private static CourierDTO getCourierDTOMock(){
        return CourierDTO.builder()
                .name("name")
                .phone("3222222")
                .photoUrl("")
                .vehiclePlate("").build();
    }

    private static ProviderDTO getProviderDTOMock(){
        return ProviderDTO.builder()
                .name("justo colombia")
                .sku("JUSTO").build();
    }

    private static StatusDTO getStatusDTOMock(){
        return StatusDTO.builder()
                .code("DELIVERED")
                .createdAt("2024-03-04T20:39:12")
                .description("execute change status from localserver")
                .id(1L)
                .name("Entregado")
                .trace(getStatusTraceDTOMock())
                .build();
    }

    private static List<StatusTraceDTO> getStatusTraceDTOMock(){
        StatusTraceDTO statusTraceDTO = StatusTraceDTO.builder()
                .createdAt("2024-05-05 10:22:00")
                .description("description")
                .id(23L)
                .name("Tu aliado va camino a la tienda").build();
        return Collections.singletonList(statusTraceDTO);
    }








}
