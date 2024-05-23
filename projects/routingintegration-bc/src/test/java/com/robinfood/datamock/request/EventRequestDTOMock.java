package com.robinfood.datamock.request;

import com.robinfood.dtos.createeventflow.request.EventRequestDTO;

public class EventRequestDTOMock {

    public static EventRequestDTO build() {
        return EventRequestDTO.builder()
                .eventId("1db0e433-6697-4050-8b8c-64cbb28227d3")
                .flowId(1L)
                .payload("{'notes':'Se entrega la orden al domicilario','orderId':''," +
                        "'orderUuid':'b3ba6d34-f9ba-4483-b648-15f3990d7b4a','origin':'Local Server'," +
                        "'statusCode':'ORDER_PROCESS','statusId':2,'userId':1}")
                .build();
    }
}
